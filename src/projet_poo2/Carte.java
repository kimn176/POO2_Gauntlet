package projet_poo2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carte extends GridPane {

    final int size;
    final Map<String, Image> imageMap = new HashMap<>();
    public String current = "floor";
    private Button[][] panes;

    public Carte(int size){
        super();
        /*
        super.setVgap(10);
        super.setHgap(10);
         */
        this.size = size;
        this.panes = new Button[this.size][this.size];
        this.loadImage();
        this.init();
    }
    
    private void loadImage(){
        
        List<String> imageNameList = new ArrayList<>(){{
            add("exit");
            add("key");
            add("keyring");
            add("potion_life");
            add("potion_defense");
            add("potion_magic");
            add("potion_physical");
            add("potion_poison");
            add("potion_speed");
            add("food");
            add("treasure");
            add("wall");
            add("smart_bomb");
            add("spawner_ghost");
            add("spawner_grunt");
            add("floor");
        }};

        for(String imageName : imageNameList)
            imageMap.put(imageName, new Image(getClass().getResource("sprites/"+imageName+".png").toExternalForm()));
        
    }

    public final Map<String, Image> allPage(){
        return imageMap;
    }

    public void init(){

        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                generatePane(x, y);
            }
        }

    }

    public void generatePane(int x, int y){

        Image image = this.allPage().get("floor");

        Button button = new Button();
        button.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        button.setBorder(border);

        button.setOnAction(action -> {
            System.out.println("Button "+x+" "+y);
            setCell(x, y, allPage().get(current));
        });

        button.setBackground(new Background(new BackgroundImage(image, null, null, null, null)));

        panes[x][y] = button;

        super.add(button, x, y);
    }

    public void setCell(int x, int y, Image image){

        if(x>size || y>size)
            return;

        panes[x][y].setBackground(new Background(new BackgroundImage(image, null, null, null, null)));

    }

    public static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

}
