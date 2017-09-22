package breder.test.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import breder.test.model.ITest;
import breder.test.model.TestModel;
import breder.test.tree.TestTree;
import breder.test.tree.TestTreeNode;
import breder.util.swing.BFrame;
import breder.util.swing.tree.AbstractTreeNode;

public class MainFrame extends BFrame {

  private static final MainFrame instance = new MainFrame();

  private TestTree tree;

  private JPanel panel;

  private ITest test;

  private ConsoleText console;

  private JLabel statusLabel;

  private MainFrame() {
    super(null);
    this.setTitle("Breder Test - Main");
    this.setJMenuBar(new MainFrameMenu(this));
    this.build();
    this.pack();
    this.setMinimumSize(this.getSize());
    this.setSize(800, 600);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setLocationRelativeTo(null);
    sun.misc.SharedSecrets.getJavaLangAccess().registerShutdownHook(2,
      new Runnable() {
        @Override
        public void run() {
          TestModel.getInstance().save();
        }
      });
    //    if (TestModel.getInstance().getTests().size() > 0) {
    //			this.tree.getSelectionModel().setSelectionPath(
    //					new TreePath(new TestTreeNode(null, TestModel.getInstance()
    //							.getTests().get(0))));
    //    }
    this.refresh();
  }

  @Override
  public MainFrameMenu getJMenuBar() {
    return (MainFrameMenu) super.getJMenuBar();
  }

  private void build() {
    this.setLayout(new BorderLayout());
    this.add(this.buildTree(), BorderLayout.WEST);
    this.add(this.buildCenter(), BorderLayout.CENTER);
    this.add(this.buildTip(), BorderLayout.SOUTH);
  }

  private Component buildTip() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel.add(this.statusLabel = new JLabel());
    return panel;
  }

  private Component buildCenter() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(this.buildEditor(), BorderLayout.CENTER);
    panel.add(this.buildConsole(), BorderLayout.SOUTH);
    return panel;
  }

  private Component buildConsole() {
    this.console = new ConsoleText();
    JScrollPane scroll = new JScrollPane(console);
    scroll.setPreferredSize(new Dimension(100, 100));
    return scroll;
  }

  private Component buildTree() {
    JScrollPane scroll = new JScrollPane(this.tree = new TestTree());
    scroll.setPreferredSize(new Dimension(300, 100));
    return scroll;
  }

  private Component buildEditor() {
    this.panel = new JPanel(new BorderLayout());
    JScrollPane scroll = new JScrollPane(this.panel);
    return scroll;
  }

  public static MainFrame getInstance() {
    return instance;
  }

  public void openTest(ITest test) {
    this.test = test;
    TestPanel panel = new TestPanel(test);
    this.panel.removeAll();
    this.panel.add(panel, BorderLayout.CENTER);
    this.panel.getParent().getParent().validate();
    this.panel.repaint();
  }

  public ITest getTest() {
    return this.test;
  }

  public void appendConsole(String text) {
    this.console.append(text);
  }

  public void cleanConsole() {
    this.console.clean();
  }

  public void refresh() {
    this.tree.getModel().refresh();
    this.tree.validate();
    this.tree.invalidate();
    this.tree.repaint();
    {
      List<ITest> tests = TestModel.getInstance().getTests();
      int n = 0;
      for (ITest test : tests) {
        if (test.getOk() != null && test.getOk() == false) {
          n++;
        }
      }
      this.statusLabel.setText(String.format(
        "Breder Language with %d Tests in which %d failed.", tests.size(), n));
    }
    if (this.test != null) {
      this.openTest(this.test);
    }
  }

  public ITest[] getTreeTests() {
    List<ITest> tests = new ArrayList<ITest>();
    AbstractTreeNode[] nodes = this.tree.getSelectNodes();
    for (AbstractTreeNode node : nodes) {
      if (node instanceof TestTreeNode) {
        TestTreeNode tnode = (TestTreeNode) node;
        tests.add(tnode.getTest());
      }
    }
    return tests.toArray(new ITest[tests.size()]);
  }

}
