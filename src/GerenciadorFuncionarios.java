import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Funcionario {
    private String nome;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}

class FuncionarioModel {
    private List<Funcionario> funcionarios;

    public FuncionarioModel() {
        funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void editarFuncionario(int index, Funcionario funcionario) {
        funcionarios.set(index, funcionario);
    }

    public void excluirFuncionario(int index) {
        funcionarios.remove(index);
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public double calcularSalarioTotal() {
        double salarioTotal = 0;
        for (Funcionario funcionario : funcionarios) {
            salarioTotal += funcionario.getSalario();
        }
        return salarioTotal;
    }
}

class FuncionarioController {
    private FuncionarioModel model;

    public FuncionarioController(FuncionarioModel model) {
        this.model = model;
    }

    public void adicionarFuncionario(String nome, String cargo, double salario) {
        Funcionario funcionario = new Funcionario(nome, cargo, salario);
        model.adicionarFuncionario(funcionario);
    }

    public void editarFuncionario(int index, String nome, String cargo, double salario) {
        Funcionario funcionario = model.getFuncionarios().get(index);
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setSalario(salario);
        model.editarFuncionario(index, funcionario);
    }

    public void excluirFuncionario(int index) {
        model.excluirFuncionario(index);
    }

    public List<Funcionario> getFuncionarios() {
        return model.getFuncionarios();
    }

    public double calcularSalarioTotal() {
        return model.calcularSalarioTotal();
    }
}

public class GerenciadorFuncionarios extends JFrame {
    private FuncionarioController controller;
    private DefaultTableModel tableModel;
    private JTable tabelaFuncionarios;
    private JLabel labelSalarioTotal;

    public GerenciadorFuncionarios() {
        // Configurações da janela
        setTitle("Gerenciador de Funcionários");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Cria o modelo da tabela
        String[] colunas = {"Nome", "Cargo", "Salário"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaFuncionarios = new JTable(tableModel);

        // Adiciona a tabela em uma barra de rolagem
        JScrollPane scrollPane = new JScrollPane(tabelaFuncionarios);

        // Adiciona os componentes ao painel
        JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));
        adicionarComponentes(painel);

        // Adiciona o painel e a tabela à janela
        add(painel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Inicializa o controlador
        FuncionarioModel model = new FuncionarioModel();
        controller = new FuncionarioController(model);
    }

    private void adicionarComponentes(JPanel painel) {
        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField(20);
        JLabel labelCargo = new JLabel("Cargo:");
        JTextField campoCargo = new JTextField(20);
        JLabel labelSalario = new JLabel("Salário:");
        JTextField campoSalario = new JTextField(10);
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoEditar = new JButton("Editar");
        JButton botaoExcluir = new JButton("Excluir");
        JButton botaoCalcularSalarioTotal = new JButton("Calcular Salário Total");
        labelSalarioTotal = new JLabel("Salário Total: R$ 0.00");

        // Estilização dos componentes
        Font fonteNegrito = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        labelNome.setFont(fonteNegrito);
        labelCargo.setFont(fonteNegrito);
        labelSalario.setFont(fonteNegrito);
        botaoAdicionar.setBackground(new Color(63, 195, 128));
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.setFocusPainted(false); // Remove o contorno do botão
        botaoEditar.setBackground(new Color(65, 131, 215));
        botaoEditar.setForeground(Color.WHITE);
        botaoEditar.setFocusPainted(false);
        botaoExcluir.setBackground(new Color(233, 87, 63));
        botaoExcluir.setForeground(Color.WHITE);
        botaoExcluir.setFocusPainted(false);
        botaoCalcularSalarioTotal.setBackground(new Color(52, 152, 219));
        botaoCalcularSalarioTotal.setForeground(Color.WHITE);
        botaoCalcularSalarioTotal.setFocusPainted(false);
        labelSalarioTotal.setFont(fonteNegrito);
        labelSalarioTotal.setForeground(new Color(155, 89, 182));

        // Adiciona borda arredondada aos botões
        Border border = BorderFactory.createLineBorder(Color.WHITE);
        Border roundedBorder = BorderFactory.createRaisedSoftBevelBorder();
        botaoAdicionar.setBorder(BorderFactory.createCompoundBorder(roundedBorder, border));
        botaoEditar.setBorder(BorderFactory.createCompoundBorder(roundedBorder, border));
        botaoExcluir.setBorder(BorderFactory.createCompoundBorder(roundedBorder, border));
        botaoCalcularSalarioTotal.setBorder(BorderFactory.createCompoundBorder(roundedBorder, border));

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelCargo);
        painel.add(campoCargo);
        painel.add(labelSalario);
        painel.add(campoSalario);
        painel.add(botaoAdicionar);
        painel.add(botaoEditar);
        painel.add(botaoExcluir);
        painel.add(botaoCalcularSalarioTotal);
        painel.add(labelSalarioTotal);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String cargo = campoCargo.getText();
                double salario = Double.parseDouble(campoSalario.getText());
                controller.adicionarFuncionario(nome, cargo, salario);
                atualizarTabela();
            }
        });

        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarFuncionario();
            }
        });

        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirFuncionario();
            }
        });

        botaoCalcularSalarioTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double salarioTotal = controller.calcularSalarioTotal();
                labelSalarioTotal.setText("Salário Total: R$ " + salarioTotal);
            }
        });
    }

    private void editarFuncionario() {
        int linhaSelecionada = tabelaFuncionarios.getSelectedRow();
        if (linhaSelecionada != -1) {
            String nome = JOptionPane.showInputDialog(this, "Novo nome:");
            String cargo = JOptionPane.showInputDialog(this, "Novo cargo:");
            String salarioStr = JOptionPane.showInputDialog(this, "Novo salário:");
            double salario = Double.parseDouble(salarioStr);
            controller.editarFuncionario(linhaSelecionada, nome, cargo, salario);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para editar.");
        }
    }

    private void excluirFuncionario() {
        int linhaSelecionada = tabelaFuncionarios.getSelectedRow();
        if (linhaSelecionada != -1) {
            controller.excluirFuncionario(linhaSelecionada);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário para excluir.");
        }
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Funcionario> funcionarios = controller.getFuncionarios();
        for (Funcionario funcionario : funcionarios) {
            tableModel.addRow(new Object[]{funcionario.getNome(), funcionario.getCargo(), funcionario.getSalario()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorFuncionarios().setVisible(true);
            }
        });
    }
}

