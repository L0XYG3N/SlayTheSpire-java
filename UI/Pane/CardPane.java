package UI.Pane;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import java.awt.*;
import UI.Listener.CardPaneListener;
import Game.BaseObject;
import Game.Card;
import Game.CardGetter;


// 카드 외형 보여주는 클래스
public class CardPane extends JLayeredPane{
    private int originalX, originalY;
    public static final int WIDTH = 150;
    public static final int HEIGHT = 220;
    public final Card card;
    public final boolean moveAble;
    
    public CardPane(int cardX, int cardY, Card card, boolean moveAble) {
        originalX = cardX;
        originalY = cardY;
        
        this.card = card;
        this.moveAble = moveAble;
        setFont(new Font("Inter", Font.PLAIN, 24));
        setOpaque(true);
        setBounds(cardX, cardY, WIDTH, HEIGHT);
        setSize(WIDTH,HEIGHT);

        /*
        class cardAppear extends Thread {
            public void run() {
                int currentX = originalX;
                int currentY = originalY + HEIGHT;
                while(currentY >= originalY) {
                    
                    currentY -= 2;
                    moveTo(currentX,currentY);
                    try {
                        Thread.sleep(1);
                    } catch(InterruptedException e) {
                        return;
                    }
                }
            }
        }
        new cardAppear().start();
        카드 생성시 등장 애니메이션, 추후수정
        */

        if(moveAble) {
            CardPaneListener listener = new CardPaneListener(this);
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }
        

        initCardPane();
    }

    JLabel cardTitle;
    JLabel cardImage;
    JLabel cardCost;
    JLabel description;

    private int titleWidth;
    private int titleHeight;
    private int descriptionWidth;
    private int descriptionHeight;
    private final int border_width = HEIGHT / 20;

    

    private void initComponentSize() {
        titleWidth = WIDTH - border_width * 2;
        titleHeight = HEIGHT / 8;

        descriptionWidth = WIDTH - 4 * border_width;
        descriptionHeight = (int)(HEIGHT / 3);
    }

    TitledBorder costBorder;

    private void initCardPane() {
        initComponentSize();
        //임시 색상 지정, switch문으로 색상 다시 지정할 예정
        Color cardColor = Color.LIGHT_GRAY;
        Color descriptionColor = Color.BLACK;
        Color titleColor = Color.WHITE;
        //
        

        // 카드 외형
        setBackground(cardColor);
        EtchedBorder eb = new EtchedBorder(
            EtchedBorder.LOWERED,
            new Color(140, 204, 203, 255),
            new Color(140, 204, 203, 255)
        );

        String cost = Integer.toString(this.card.getCost());
        Font costFont = new Font("Roman", Font.PLAIN, 18); // cost font

        cardCost = new JLabel(cost,SwingConstants.CENTER);
        cardCost.setBounds(0,0,25,25);
        cardCost.setBackground(Color.ORANGE);
        cardCost.setOpaque(true);
        cardCost.setFont(costFont);
        add(cardCost);

        BorderLayout borderLayout = new BorderLayout(0,-5);

        this.setBorder(eb);
        this.setLayout(borderLayout);

        // 카드 이름
        cardTitle = new JLabel("", SwingConstants.CENTER);
        Font titleFont = new Font("Dialog", Font.ITALIC, 16);
        cardTitle.setFont(titleFont);
        cardTitle.setOpaque(true);
        cardTitle.setBackground(Color.black);
        cardTitle.setForeground(Color.white);
        cardTitle.setText(this.card.getCardName());
        cardTitle.setSize(titleWidth, titleHeight);
        cardTitle.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        add(this.cardTitle, BorderLayout.NORTH);


        // 카드 설명
        //description = new JTextPane();
        description = new JLabel();

        //description.setForeground(Color.WHITE); // 폰트 색상
        description.setBackground(descriptionColor);
        description.setLayout(new FlowLayout());
        description.setAlignmentY(Component.CENTER_ALIGNMENT); 
        description.setFocusable(false);
        description.setOpaque(true);
        description.setHorizontalAlignment(SwingConstants.CENTER);
        description.setVerticalAlignment(SwingConstants.TOP);
        description.setFont(new Font("", Font.PLAIN, 11));
        description.setText(this.card.getDescription());

        description.setPreferredSize(new Dimension(
                descriptionWidth,
                descriptionHeight
        )); 
            
            
        description.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(description, BorderLayout.SOUTH);


        //Set image part
        ImageIcon img = new ImageIcon(card.getImagePath());
        Image tempImg = img.getImage();
        tempImg = tempImg.getScaledInstance(140,110,Image.SCALE_SMOOTH);
        img = new ImageIcon(tempImg);
        cardImage = new JLabel(img);
        cardImage.setBackground(cardColor);
        
        cardImage.setSize(WIDTH, descriptionHeight);
        LineBorder imageLineBorder = new LineBorder(titleColor, 4);
        TitledBorder imageBorder = new TitledBorder(
                imageLineBorder,
                "cardType",
                TitledBorder.CENTER,
                TitledBorder.BOTTOM
        );
        Font typeFont = new Font("ROMAN", Font.PLAIN, 10);
        imageBorder.setTitleColor(Color.WHITE);
        imageBorder.setTitleFont(typeFont);
        cardImage.setBorder(imageBorder);
        this.setLayer(cardImage,2);
        this.add(cardImage, BorderLayout.CENTER);
        

        this.setVisible(true);
    }


    public void updateDescription() {
        description.setText(this.card.getDescription());
    }

    

    public void moveTo(int x, int y) {
        this.setLocation(x, y);
    }

    public void moveBack() {
        moveTo(originalX, originalY);
    }

    public void useCard(BaseObject obj) {
        card.use(obj);
        getParent().remove(this);
        
    }


    //테스트용 실행 코드
    // public static void main(String [] args) {
    //     JFrame frame = new JFrame();
    //     frame.setSize(500, 500);
    //     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //     frame.setResizable(false);
    //     frame.setLayout(null);
    //     CardPane pane = new CardPane(50, 50, CardGetter.GetCardById(-1)
    //     );
    //     frame.add(pane);
    //     frame.setVisible(true);
    // }
}





