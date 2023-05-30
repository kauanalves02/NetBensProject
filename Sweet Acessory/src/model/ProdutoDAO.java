
package model;

import model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class ProdutoDAO {
    private Connection con;

    public ProdutoDAO() {
        con = new Conexao().conectar();
    }

    public ArrayList<Produto> listar() {
        String sql = "SELECT * FROM produto";
        ArrayList<Produto> produtos;
        produtos = new ArrayList<Produto>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto c = new Produto();
                c.setId(rs.getInt("id"));
                c.setMateriais(rs.getString("materiais"));
                c.setValores(rs.getString("valores"));
                produtos.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return produtos;
    }

    public void inserir(Produto c) {
        String sql = "INSERT INTO produto(materiais,valores) VALUE(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getMateriais());
            ps.setString(2, c.getValores());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(Produto c) {
        String sql = "UPDATE produto SET materiais=?,valores=? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getMateriais());
            ps.setString(2, c.getValores());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "produto renovado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(Produto c) {
        String sql = "DELETE FROM contato WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "produto excluido");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


