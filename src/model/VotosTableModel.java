/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import dao.RestauranteDaoImpl;
import dao.VotoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kassi
 */
public class VotosTableModel extends AbstractTableModel{
    
    private List<Voto> listaVotos = new ArrayList<>();
    private RestauranteDaoImpl daoRestaurante = new RestauranteDaoImpl();

    //colunas da tabela
    private String[] colunas = new String[]{"CPF", "Restaurante", "Data"};
    
    public VotosTableModel() {}
    
    public VotosTableModel(List<Voto> listaVotos) {
        this.listaVotos = listaVotos;
        
    }
    
     @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        if (!listaVotos.isEmpty()) {
            return listaVotos.size();
        } else {
            return 0;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
    
    /**
     * informa o tipo de dado da coluna
     * @param columnIndex
     * @return classe do tipo de dado
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        switch (columnIndex) {
            case 0:
                return byte.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
        }
        return null;
    }

    /**
     * Retorna o campo referente à coluna selecionada. O Switch verifica qual a
     * coluna retornar
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Voto voto = listaVotos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return voto.getCpf();
            case 1:
                return voto.getRestaurante().getNome();
            case 2:
                return util.Utils.dateToString(voto.getData());
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /**
     * Seta valor na linha e coluna selecionada. O Switch verifica qual coluna
     * setar
     *
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Voto voto = listaVotos.get(rowIndex);

        switch (columnIndex) {

            case 1:
                voto.setRestaurante(daoRestaurante.findByName(aValue.toString()));
                break;
            case 2:
                voto.setData(util.Utils.stringToDate(aValue.toString()));
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
        VotoDaoImpl dao = new VotoDaoImpl();
        dao.update(voto);
    }

    /**
     * seta um valor para a linha selecionada
     * @param prod
     * @param rowIndex 
     */
    public void setValueAtRow(Voto v, int rowIndex) {
        
        Voto voto = listaVotos.get(rowIndex);

        voto.setRestaurante(v.getRestaurante());
        voto.setData(v.getData());

        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
    }

    
    /**
     * autoriza a edicao das colunas
     * a menos que seja a coluna 0
     * @param rowIndex
     * @param columnIndex
     * @return isCellEditable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return false;
        }else{
            return true;
        }
    }

    public Voto getVoto(int rowIndex) {
        return listaVotos.get(rowIndex);
    }

    /**
     * Adiciona um voto na JTable
     *
     * @param voto
     */
    public void addVoto(Voto v) {
        listaVotos.add(v);
        int lastIndex = getRowCount() - 1;

        fireTableRowsInserted(lastIndex, lastIndex);
    }

    /**
     * Adiciona uma lista de produtos ao final dos registros
     *
     * @param listaVotos
     */
    public void addListProduto(List<Voto> listaV) {
        int position = getRowCount() - 1;
        int oldSize = getRowCount();
        this.listaVotos.addAll(listaV);

        fireTableRowsInserted(oldSize, position);
    }

    /**
     * Remove todos os registros
     */
    public void clear() {
        listaVotos.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return listaVotos.isEmpty();
    }
}
