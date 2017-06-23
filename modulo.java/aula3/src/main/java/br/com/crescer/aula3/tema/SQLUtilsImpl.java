package br.com.crescer.aula3.tema;

import br.com.crescer.aula2.tema.ReaderUtilsImpl;
import br.com.crescer.aula2.tema.WriterUtilsImpl;
import br.com.crescer.aula3.ConnectionUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class SQLUtilsImpl implements SQLUtils {
    
    @Override
    public void runFile(String filename) {
        if(filename == null || !filename.endsWith(".sql")) 
            throw new RuntimeException("Arquivo inválido.");
        try (final Statement statement = ConnectionUtils.getConnection().createStatement()) {
            final String queries = new ReaderUtilsImpl().read(filename);
            for(String query : queries.split(";")) {
                statement.addBatch(query);
            }
            statement.executeBatch();
        } catch (Exception ex) {
            throw new RuntimeException("Erro: " + ex.getMessage());
        }
    }

    @Override
    public String executeQuery(String query) {
        if(query == null)
            throw new RuntimeException("Query inválida.");
        try (final Statement statement = ConnectionUtils.getConnection().createStatement();
                final ResultSet rs = statement.executeQuery(query);) {
            return generateCSV(rs);
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void importCSV(File file) {
        if(file == null)
            throw new RuntimeException("Arquivo inválido.");
        final String nomeArquivo = file.getName();
        if(nomeArquivo == null || !nomeArquivo.endsWith(".csv") || file.isDirectory() || !file.exists())
            throw new RuntimeException("Arquivo inválido.");
        final String tabela = nomeArquivo.substring(0, nomeArquivo.lastIndexOf("."));
        try (
                final Reader reader = new FileReader(file);
                final BufferedReader bufferReader = new BufferedReader(reader);
            ) {
            List<String> linhas = bufferReader.lines().collect(toList());
            if(linhas.size() <= 1) return;
            final String QUERY = String.format(
                    "insert into %s (%s) values (?)", 
                    tabela,
                    linhas.get(0)
            );
            try (final Statement statement = ConnectionUtils.getConnection().createStatement()) {
                for(int i = 1; i < linhas.size(); i++) {
                    statement.addBatch(QUERY.replace("?", linhas.get(i)));
                }
                statement.executeBatch();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    @Override
    public File exportCSV(String query) {
        try {
            String csv = executeQuery(query);
            String path = "target/query.csv";
            new WriterUtilsImpl().write(path, csv);
            return new File(path);
        } catch (Exception ex) {
            throw new RuntimeException("Erro: " + ex.getMessage());
        }
    }
    
    private static String generateCSV(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int quantColunas = rsmd.getColumnCount();
        if(quantColunas == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= quantColunas; i++) {
            sb.append(rsmd.getColumnLabel(i)).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(",")).append("\n");
        while(rs.next()) {
            for(int i = 1; i <= quantColunas; i++) {
                sb.append(rs.getString(i)).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(",")).append("\n");
        }
        return sb.toString();
    }
    
}
