/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.data;

import static com.mycompany.pirate.data.FileRef.FONT_CASINO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author BEN JAAFAR
 */
public class values {
    public static final Color GREEN_COLOR_BACKGROUND = new Color(34, 111, 55);
    public static final Color RED_CUSTOM = new Color(170,39,23);
    public static final Color GREEN_CUSTOM = new Color(60,63,65);
    public static final Color BLACK_CUSTOM = new Color(74,32,41);
    public static final Color BLUE_CUSTOM = new Color(108,154,175);
    public static final Color INFO_PANEL_COLOR_BACKGROUND = new Color(24, 101, 45);
    public static final Color TRANSPARENT_COLOR_BACKGROUND = new Color(255, 0, 0, 0);
    
    public static final int MAX_LIFE = 3;
    
    //need fix not working
    public static void usingCustomFonts() {
      GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
      List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
      try {
          List<File> LIST = Arrays.asList(
            new File(FONT_CASINO)
          );
          for (File LIST_ITEM : LIST) {
              if (LIST_ITEM.exists()) {
                  Font FONT = Font.createFont(Font.TRUETYPE_FONT, LIST_ITEM);
                  if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
                      GE.registerFont(FONT);
                  }
              }
          }
      } catch (FontFormatException | IOException exception) {
          JOptionPane.showMessageDialog(null, exception.getMessage());
      }
  }

}
