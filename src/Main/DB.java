package Main;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class SupermarketManagementSystem extends JFrame {
    // Main panels
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    
    // Components
    private JTable productTable;
    private JTextField searchField;
    private JButton[] menuButtons;
    private JButton[] actionButtons;
    private JButton[] pageButtons;
    
    // Constants
    private final String[] MENU_ITEMS = {
        "Tổng quan", "Quản lý sản phẩm", "Nhập hàng", "Xuất hàng", 
        "Báo cáo & Thống kê", "Quản lý nhà cung cấp", "Cài đặt hệ thống"
    };
    
    private final String[] SUMMARY_TITLES = {
        "Sản phẩm", "Tồn kho", "Sắp hết hàng", "Nhà cung cấp"
    };
    
    private final String[] SUMMARY_VALUES = {
        "1,245", "5,789", "37", "48"
    };
    
    private final Color[] SUMMARY_COLORS = {
        new Color(231, 76, 60),   // Red
        new Color(46, 204, 113),  // Green
        new Color(241, 196, 15),  // Yellow
        new Color(155, 89, 182)   // Purple
    };
    
    private final Color SIDEBAR_BG = new Color(44, 62, 80);
    private final Color ACTIVE_MENU_BG = new Color(52, 73, 94);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 16);
    private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);
    
    public SupermarketManagementSystem() {
        setTitle("PHẦN MỀM QUẢN LÝ HÀNG HÓA SIÊU THỊ");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main layout
        mainPanel = new JPanel(new BorderLayout(0, 0));
        setContentPane(mainPanel);
        
        // Create sidebar and content panels
        createSidebarPanel();
        createContentPanel();
        
        // Add panels to main layout
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }
    
    private void createSidebarPanel() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(SIDEBAR_BG);
        sidebarPanel.setPreferredSize(new Dimension(220, getHeight()));
        sidebarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(52, 73, 94)));
        
        // Add application title
        JLabel titleLabel = new JLabel("QUẢN LÝ SIÊU THỊ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 58, 74));
        titlePanel.setMaximumSize(new Dimension(220, 60));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(titleLabel);
        
        sidebarPanel.add(titlePanel);
        
        // Add menu items
        menuButtons = new JButton[MENU_ITEMS.length];
        
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            final int index = i;
            JButton button = new JButton(MENU_ITEMS[i]);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setFont(NORMAL_FONT);
            button.setForeground(Color.WHITE);
            button.setBackground(i == 1 ? ACTIVE_MENU_BG : SIDEBAR_BG); // Set "Quản lý sản phẩm" active
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setMaximumSize(new Dimension(220, 50));
            button.setPreferredSize(new Dimension(220, 50));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    activateMenuItem(index);
                }
            });
            
            menuButtons[i] = button;
            sidebarPanel.add(button);
        }
        
        // Add space filler
        sidebarPanel.add(Box.createVerticalGlue());
        
        // Add quick stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(SIDEBAR_BG);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        statsPanel.setMaximumSize(new Dimension(220, 160));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel statsLabel = new JLabel("Thống kê nhanh");
        statsLabel.setForeground(Color.WHITE);
        statsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 0));
        statsPanel.add(statsLabel);
        
        // Add mini chart
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Draw chart bars
                g2d.setColor(new Color(231, 76, 60));
                g2d.fillRect(20, 30, 25, 80);
                
                g2d.setColor(new Color(241, 196, 15));
                g2d.fillRect(60, 50, 25, 60);
                
                g2d.setColor(new Color(46, 204, 113));
                g2d.fillRect(100, 20, 25, 90);
                
                g2d.setColor(new Color(52, 152, 219));
                g2d.fillRect(140, 40, 25, 70);
            }
        };
        
        chartPanel.setBackground(SIDEBAR_BG);
        chartPanel.setPreferredSize(new Dimension(190, 120));
        chartPanel.setMaximumSize(new Dimension(190, 120));
        statsPanel.add(chartPanel);
        
        sidebarPanel.add(statsPanel);
    }
    
    private void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.setBackground(new Color(245, 247, 250));
        
        // Create and add header panel with title bar
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        JLabel headerTitle = new JLabel("PHẦN MỀM QUẢN LÝ HÀNG HÓA SIÊU THỊ");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerTitle.setForeground(Color.WHITE);
        
        headerPanel.add(headerTitle, BorderLayout.WEST);
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create main content area with padding
        JPanel mainContent = new JPanel(new BorderLayout(0, 15));
        mainContent.setBackground(new Color(245, 247, 250));
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add summary cards
        JPanel summaryPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        summaryPanel.setOpaque(false);
        
        for (int i = 0; i < SUMMARY_TITLES.length; i++) {
            JPanel card = createSummaryCard(SUMMARY_TITLES[i], SUMMARY_VALUES[i], SUMMARY_COLORS[i]);
            summaryPanel.add(card);
        }
        
        mainContent.add(summaryPanel, BorderLayout.NORTH);
        
        // Add product list panel
        JPanel productPanel = new JPanel(new BorderLayout(0, 10));
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        
        // Add product panel header
        JPanel productHeaderPanel = new JPanel(new BorderLayout());
        productHeaderPanel.setBackground(Color.WHITE);
        productHeaderPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        
        JLabel productTitle = new JLabel("Danh sách sản phẩm");
        productTitle.setFont(HEADER_FONT);
        
        // Add search field
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setText("Tìm kiếm sản phẩm...");
        searchField.setForeground(Color.GRAY);
        
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Tìm kiếm sản phẩm...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Tìm kiếm sản phẩm...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        
        productHeaderPanel.add(productTitle, BorderLayout.WEST);
        productHeaderPanel.add(searchField, BorderLayout.EAST);
        
        productPanel.add(productHeaderPanel, BorderLayout.NORTH);
        
        // Add action buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 15));
        
        actionButtons = new JButton[2];
        
        actionButtons[0] = createButton("Thêm mới", new Color(52, 152, 219));
        actionButtons[1] = createButton("Xuất Excel", new Color(46, 204, 113));
        
        actionPanel.add(actionButtons[0]);
        actionPanel.add(actionButtons[1]);
        
        productPanel.add(actionPanel, BorderLayout.CENTER);
        
        // Create product table
        createProductTable();
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        productPanel.add(tableScrollPane, BorderLayout.SOUTH);
        
        // Add pagination panel
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
        paginationPanel.setBackground(Color.WHITE);
        paginationPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
        
        pageButtons = new JButton[6];
        String[] pageLabels = {"1", "2", "3", "4", "5", "..."};
        
        for (int i = 0; i < pageLabels.length; i++) {
            JButton pageButton = new JButton(pageLabels[i]);
            pageButton.setPreferredSize(new Dimension(35, 35));
            pageButton.setFont(new Font("Arial", Font.PLAIN, 12));
            pageButton.setFocusPainted(false);
            pageButton.setBorderPainted(false);
            
            if (i == 1) { // Make "2" active
                pageButton.setBackground(new Color(52, 152, 219));
                pageButton.setForeground(Color.WHITE);
            } else {
                pageButton.setBackground(new Color(240, 240, 240));
                pageButton.setForeground(Color.BLACK);
            }
            
            pageButtons[i] = pageButton;
            paginationPanel.add(pageButton);
        }
        
        productPanel.add(paginationPanel, BorderLayout.PAGE_END);
        mainContent.add(productPanel, BorderLayout.CENTER);
        
        contentPanel.add(mainContent, BorderLayout.CENTER);
    }
    
    private JPanel createSummaryCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 22));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
        
        labelPanel.add(titleLabel);
        labelPanel.add(valueLabel);
        
        card.add(labelPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private void createProductTable() {
        // Define table columns and data
        String[] columns = {"Mã SP", "Tên sản phẩm", "Danh mục", "Tồn kho", "Giá bán", "Thao tác"};
        Object[][] data = {
            {"SP001", "Nước giặt Omo 3.5kg", "Hóa phẩm", 125, "125,000đ", ""},
            {"SP002", "Gạo Jasmine 5kg", "Thực phẩm", 43, "90,000đ", ""},
            {"SP003", "Sữa tươi Vinamilk 1L", "Sữa", 87, "28,000đ", ""},
            {"SP004", "Dầu ăn Neptune 1L", "Dầu ăn", 15, "42,000đ", ""},
            {"SP005", "Nước mắm Nam Ngư 500ml", "Gia vị", 62, "35,000đ", ""}
        };
        
        // Create table model
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only action column is editable
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) return Integer.class; // For proper numeric sorting
                return String.class;
            }
        };
        
        // Create table with custom renderer
        productTable = new JTable(model);
        productTable.setRowHeight(40);
        productTable.setShowVerticalLines(true);
        productTable.setShowHorizontalLines(true);
        productTable.setGridColor(new Color(240, 240, 240));
        productTable.setFont(new Font("Arial", Font.PLAIN, 13));
        productTable.setBorder(null);
        
        // Customize header
        JTableHeader header = productTable.getTableHeader();
        header.setBackground(new Color(245, 245, 245));
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Set column widths
        TableColumnModel columnModel = productTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);  // Mã SP
        columnModel.getColumn(1).setPreferredWidth(200); // Tên sản phẩm
        columnModel.getColumn(2).setPreferredWidth(100); // Danh mục
        columnModel.getColumn(3).setPreferredWidth(80);  // Tồn kho
        columnModel.getColumn(4).setPreferredWidth(100); // Giá bán
        columnModel.getColumn(5).setPreferredWidth(100); // Thao tác
        
        // Add button renderer for action column
        columnModel.getColumn(5).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(5).setCellEditor(new ButtonEditor());
    }
    
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(NORMAL_FONT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 35));
        
        return button;
    }
    
    private void activateMenuItem(int index) {
        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setBackground(i == index ? ACTIVE_MENU_BG : SIDEBAR_BG);
        }
    }
    
    // Custom renderer for action buttons in table
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton editButton;
        private JButton deleteButton;
        
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            setOpaque(true);
            
            editButton = new JButton();
            editButton.setPreferredSize(new Dimension(30, 30));
            editButton.setBackground(new Color(52, 152, 219));
            editButton.setForeground(Color.WHITE);
            editButton.setBorderPainted(false);
            editButton.setFocusPainted(false);
            editButton.setText("E");
            
            deleteButton = new JButton();
            deleteButton.setPreferredSize(new Dimension(30, 30));
            deleteButton.setBackground(new Color(231, 76, 60));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBorderPainted(false);
            deleteButton.setFocusPainted(false);
            deleteButton.setText("X");
            
            add(editButton);
            add(deleteButton);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }
    
    // Custom editor for action buttons in table
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;
        private JButton editButton;
        private JButton deleteButton;
        
        public ButtonEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            
            editButton = new JButton();
            editButton.setPreferredSize(new Dimension(30, 30));
            editButton.setBackground(new Color(52, 152, 219));
            editButton.setForeground(Color.WHITE);
            editButton.setBorderPainted(false);
            editButton.setFocusPainted(false);
            editButton.setText("E");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = productTable.getSelectedRow();
                    JOptionPane.showMessageDialog(null, "Sửa sản phẩm mã: " + 
                        productTable.getValueAt(row, 0));
                    fireEditingStopped();
                }
            });
            
            deleteButton = new JButton();
            deleteButton.setPreferredSize(new Dimension(30, 30));
            deleteButton.setBackground(new Color(231, 76, 60));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBorderPainted(false);
            deleteButton.setFocusPainted(false);
            deleteButton.setText("X");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = productTable.getSelectedRow();
                    JOptionPane.showMessageDialog(null, "Xóa sản phẩm mã: " + 
                        productTable.getValueAt(row, 0));
                    fireEditingStopped();
                }
            });
            
            panel.add(editButton);
            panel.add(deleteButton);
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, 
                boolean isSelected, int row, int column) {
            panel.setBackground(table.getSelectionBackground());
            return panel;
        }
        
        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
    
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SupermarketManagementSystem().setVisible(true);
            }
        });
    }
}