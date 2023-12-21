import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Complaint {
    private String description;

    public Complaint(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

class ComplaintManager {
    private List<Complaint> complaints;

    public ComplaintManager() {
        this.complaints = new ArrayList<>();
    }

    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }
}

public class ComplainManagementSystem extends JFrame {
    private JTextArea complaintTextArea;
    private ComplaintManager complaintManager;

    public ComplainManagementSystem() {
        complaintManager = new ComplaintManager();

        // Frame setup
        setTitle("Complaint Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        complaintTextArea = new JTextArea();
        JButton submitButton = new JButton("Submit Complaint");
        JButton viewButton = new JButton("View Complaints");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(viewButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(complaintTextArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event handling (Submit Button)
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String complaintDescription = complaintTextArea.getText();
                if (!complaintDescription.isEmpty()) {
                    Complaint newComplaint = new Complaint(complaintDescription);
                    complaintManager.addComplaint(newComplaint);
                    JOptionPane.showMessageDialog(ComplainManagementSystem.this,
                            "Complaint submitted successfully!");
                    complaintTextArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(ComplainManagementSystem.this,
                            "Please enter a complaint before submitting.");
                }
            }
        });

        // Event handling (View Button)
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Complaint> complaints = complaintManager.getComplaints();
                if (!complaints.isEmpty()) {
                    StringBuilder complaintList = new StringBuilder("Complaints:\n");
                    for (Complaint complaint : complaints) {
                        complaintList.append("- ").append(complaint.getDescription()).append("\n");
                    }
                    JOptionPane.showMessageDialog(ComplainManagementSystem.this,
                            complaintList.toString(), "View Complaints", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ComplainManagementSystem.this,
                            "No complaints submitted yet.", "View Complaints", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ComplainManagementSystem().setVisible(true);
            }
        });
    }
}
