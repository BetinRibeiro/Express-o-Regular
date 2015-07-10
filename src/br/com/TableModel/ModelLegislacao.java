package br.com.TableModel;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.Bin.ArtigoLei;

import javax.swing.table.AbstractTableModel;

public class ModelLegislacao extends AbstractTableModel {

	private List<ArtigoLei> dados;
	private String[] colunas = { "Indentificador","Art", "Lei", "Prioridade", "Tamanho", "Percentual"};
	DecimalFormat df = new DecimalFormat("0.00");

	// você precisar que os dados também sejam imediatamente alterados no banco
	// de dados por exemplo,
	// você vai precisar adicionar um TableModelListener ao seu model que
	// executará o método
	// tableChanged toda vez que os dados da JTable forem alterados.
	public ModelLegislacao() {
		dados = new ArrayList<ArtigoLei>();

	}

	public void addRow(ArtigoLei p) {

		this.dados.add(p);
		this.fireTableDataChanged();
	}

	public void removeTudo() {

		this.dados.clear();
		this.fireTableDataChanged();
	}

	public String getColumnName(int num) {
		return this.colunas[num];
	}

	// Também iremos precisar de um método que remova uma linha da tabela
	public int removeRow(int linha) {
		int id = this.dados.get(linha).getId();
		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
		return id;
	}

	// Se você deve lembrar quando utilizávamos o DefaultTableModel podíamos
	// alterar a tabela
	// simplesmente dando um duplo clique em cima e alguma célula e ela
	// permitiria a edição.
	// Isso acontecia porque no DefaultTableModel o método isCellEditable(int
	// linha, int coluna)
	// que é chamado para saber se uma célula é editável sempre retornava true,
	// mas no AbstractTableModel
	// ele retorna sempre false, então devemos sobrescreve-lo
	public boolean isCellEditable(int linha, int coluna) {
		return true;
	}

	// Estes métodos devem retornar respectivamente o numero de linhas, o numero
	// de colunas
	// e o valor da célula correspondente aos valores de linha e coluna
	// fornecidos por parâmetro.
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {

		case 0:
			return dados.get(linha).getId();
		case 1:
			return dados.get(linha).getNome();
		case 2:
			return dados.get(linha).getLei();
		case 3:
			return df.format(dados.get(linha).getPrioridade());
		case 4:
			return dados.get(linha).getConteudo().length();
		case 5:
			return df.format((dados.get(linha).getPrioridade()/dados.get(linha).getConteudo().length())*100)+"%";

		}
		return null;
	}
}
