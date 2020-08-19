/*     */ package com.gildedgames.launcher.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public class ComponentResizer extends java.awt.event.MouseAdapter
/*     */ {
/*  19 */   private static final Dimension MINIMUM_SIZE = new Dimension(10, 10);
/*  20 */   private static final Dimension MAXIMUM_SIZE = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
/*     */   
/*  22 */   private static Map<Integer, Integer> cursors = new HashMap();
/*     */   
/*     */   private Insets dragInsets;
/*     */   
/*     */   private Dimension snapSize;
/*     */   
/*     */   private int direction;
/*     */   
/*     */   protected static final int NORTH = 1;
/*     */   
/*     */   protected static final int WEST = 2;
/*     */   
/*     */   protected static final int SOUTH = 4;
/*     */   
/*     */   protected static final int EAST = 8;
/*     */   
/*     */   private Cursor sourceCursor;
/*     */   
/*     */   private boolean resizing;
/*     */   
/*     */   private Rectangle bounds;
/*     */   
/*     */   private Point pressed;
/*     */   
/*     */   private boolean autoscrolls;
/*     */   private Dimension minimumSize;
/*     */   private Dimension maximumSize;
/*     */   private boolean disabled;
/*     */   
/*     */   public boolean isDisabled()
/*     */   {
/*  53 */     return this.disabled;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComponentResizer()
/*     */   {
/*  61 */     this(new Insets(5, 5, 5, 5), new Dimension(1, 1), new Component[0]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComponentResizer(Component... components)
/*     */   {
/*  72 */     this(new Insets(5, 5, 5, 5), new Dimension(1, 1), components);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComponentResizer(Insets dragInsets, Component... components)
/*     */   {
/*  85 */     this(dragInsets, new Dimension(1, 1), components);
/*     */   }
/*     */   
/*     */   public ComponentResizer(Insets dragInsets, Dimension snapSize, Component... components)
/*     */   {
/*  25 */     cursors.put(Integer.valueOf(1), Integer.valueOf(8));
/*  26 */     cursors.put(Integer.valueOf(2), Integer.valueOf(10));
/*  27 */     cursors.put(Integer.valueOf(4), Integer.valueOf(9));
/*  28 */     cursors.put(Integer.valueOf(8), Integer.valueOf(11));
/*  29 */     cursors.put(Integer.valueOf(3), Integer.valueOf(6));
/*  30 */     cursors.put(Integer.valueOf(9), Integer.valueOf(7));
/*  31 */     cursors.put(Integer.valueOf(6), Integer.valueOf(4));
/*  32 */     cursors.put(Integer.valueOf(12), Integer.valueOf(5));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  50 */     this.minimumSize = MINIMUM_SIZE;
/*  51 */     this.maximumSize = MAXIMUM_SIZE;
/*     */     
/*  53 */     this.disabled = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  98 */     setDragInsets(dragInsets);
/*  99 */     setSnapSize(snapSize);
/* 100 */     registerComponent(components);
/*     */   }
/*     */   
/*     */   public void setDisabled(boolean val) {
/* 104 */     if (!val) {
/* 105 */       this.resizing = false;
/*     */     }
/*     */     
/* 108 */     this.disabled = val;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Insets getDragInsets()
/*     */   {
/* 116 */     return this.dragInsets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDragInsets(Insets dragInsets)
/*     */   {
/* 129 */     validateMinimumAndInsets(this.minimumSize, dragInsets);
/*     */     
/* 131 */     this.dragInsets = dragInsets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMaximumSize()
/*     */   {
/* 140 */     return this.maximumSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaximumSize(Dimension maximumSize)
/*     */   {
/* 150 */     this.maximumSize = maximumSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMinimumSize()
/*     */   {
/* 159 */     return this.minimumSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMinimumSize(Dimension minimumSize)
/*     */   {
/* 169 */     validateMinimumAndInsets(minimumSize, this.dragInsets);
/*     */     
/* 171 */     this.minimumSize = minimumSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void deregisterComponent(Component... components)
/*     */   {
/* 180 */     for (Component component : components) {
/* 181 */       component.removeMouseListener(this);
/* 182 */       component.removeMouseMotionListener(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void registerComponent(Component... components)
/*     */   {
/* 192 */     for (Component component : components) {
/* 193 */       component.addMouseListener(this);
/* 194 */       component.addMouseMotionListener(this);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getSnapSize()
/*     */   {
/* 204 */     return this.snapSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSnapSize(Dimension snapSize)
/*     */   {
/* 216 */     this.snapSize = snapSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void validateMinimumAndInsets(Dimension minimum, Insets drag)
/*     */   {
/* 225 */     int minimumWidth = drag.left + drag.right;
/* 226 */     int minimumHeight = drag.top + drag.bottom;
/*     */     
/* 228 */     if ((minimum.width < minimumWidth) || (minimum.height < minimumHeight))
/*     */     {
/* 230 */       String message = "Minimum size cannot be less than drag insets";
/* 231 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent e)
/*     */   {
/* 239 */     Component source = e.getComponent();
/*     */     
/* 241 */     if (this.disabled) {
/* 242 */       source.setCursor(this.sourceCursor);
/*     */       
/* 244 */       return;
/*     */     }
/*     */     
/* 247 */     Point location = e.getPoint();
/* 248 */     this.direction = 0;
/*     */     
/* 250 */     if (location.x < this.dragInsets.left) {
/* 251 */       this.direction += 2;
/*     */     }
/* 253 */     if (location.x > source.getWidth() - this.dragInsets.right - 1) {
/* 254 */       this.direction += 8;
/*     */     }
/* 256 */     if (location.y < this.dragInsets.top) {
/* 257 */       this.direction += 1;
/*     */     }
/* 259 */     if (location.y > source.getHeight() - this.dragInsets.bottom - 1) {
/* 260 */       this.direction += 4;
/*     */     }
/*     */     
/* 263 */     if ((this.direction == 0) || (this.direction == 2) || (this.direction == 1) || (this.direction == 9) || (this.direction == 3) || (this.direction == 6))
/*     */     {
/* 265 */       source.setCursor(this.sourceCursor);
/*     */     }
/*     */     else {
/* 268 */       int cursorType = ((Integer)cursors.get(Integer.valueOf(this.direction))).intValue();
/* 269 */       Cursor cursor = Cursor.getPredefinedCursor(cursorType);
/* 270 */       source.setCursor(cursor);
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/* 276 */     if (!this.resizing) {
/* 277 */       Component source = e.getComponent();
/* 278 */       this.sourceCursor = source.getCursor();
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/* 284 */     if (!this.resizing) {
/* 285 */       Component source = e.getComponent();
/* 286 */       source.setCursor(this.sourceCursor);
/*     */     }
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 292 */     if (this.disabled) {
/* 293 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 298 */     if (this.direction == 0) { return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 303 */     this.resizing = true;
/*     */     
/* 305 */     Component source = e.getComponent();
/* 306 */     this.pressed = e.getPoint();
/* 307 */     SwingUtilities.convertPointToScreen(this.pressed, source);
/* 308 */     this.bounds = source.getBounds();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 313 */     if ((source instanceof JComponent)) {
/* 314 */       JComponent jc = (JComponent)source;
/* 315 */       this.autoscrolls = jc.getAutoscrolls();
/* 316 */       jc.setAutoscrolls(false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 325 */     if (this.disabled) {
/* 326 */       return;
/*     */     }
/*     */     
/* 329 */     this.resizing = false;
/*     */     
/* 331 */     Component source = e.getComponent();
/* 332 */     source.setCursor(this.sourceCursor);
/*     */     
/* 334 */     if ((source instanceof JComponent)) {
/* 335 */       ((JComponent)source).setAutoscrolls(this.autoscrolls);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 349 */     if ((!this.resizing) || (this.disabled)) { return;
/*     */     }
/* 351 */     Component source = e.getComponent();
/* 352 */     Point dragged = e.getPoint();
/* 353 */     SwingUtilities.convertPointToScreen(dragged, source);
/*     */     
/* 355 */     changeBounds(source, this.direction, this.bounds, this.pressed, dragged);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void changeBounds(Component source, int direction, Rectangle bounds, Point pressed, Point current)
/*     */   {
/* 361 */     int x = bounds.x;
/* 362 */     int y = bounds.y;
/* 363 */     int width = bounds.width;
/* 364 */     int height = bounds.height;
/*     */     
/*     */ 
/*     */ 
/* 368 */     if (2 == (direction & 0x2)) {
/* 369 */       int drag = getDragDistance(pressed.x, current.x, this.snapSize.width);
/* 370 */       int maximum = Math.min(width + x, this.maximumSize.width);
/* 371 */       drag = getDragBounded(drag, this.snapSize.width, width, this.minimumSize.width, maximum);
/*     */       
/* 373 */       x -= drag;
/* 374 */       width += drag;
/*     */     }
/*     */     
/* 377 */     if (1 == (direction & 0x1)) {
/* 378 */       int drag = getDragDistance(pressed.y, current.y, this.snapSize.height);
/* 379 */       int maximum = Math.min(height + y, this.maximumSize.height);
/* 380 */       drag = getDragBounded(drag, this.snapSize.height, height, this.minimumSize.height, maximum);
/*     */       
/* 382 */       y -= drag;
/* 383 */       height += drag;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 388 */     if (8 == (direction & 0x8)) {
/* 389 */       int drag = getDragDistance(current.x, pressed.x, this.snapSize.width);
/* 390 */       Dimension boundingSize = getBoundingSize(source);
/* 391 */       int maximum = Math.min(boundingSize.width - x, this.maximumSize.width);
/* 392 */       drag = getDragBounded(drag, this.snapSize.width, width, this.minimumSize.width, maximum);
/* 393 */       width += drag;
/*     */     }
/*     */     
/* 396 */     if (4 == (direction & 0x4)) {
/* 397 */       int drag = getDragDistance(current.y, pressed.y, this.snapSize.height);
/* 398 */       Dimension boundingSize = getBoundingSize(source);
/* 399 */       int maximum = Math.min(boundingSize.height - y, this.maximumSize.height);
/* 400 */       drag = getDragBounded(drag, this.snapSize.height, height, this.minimumSize.height, maximum);
/* 401 */       height += drag;
/*     */     }
/*     */     
/* 404 */     source.setBounds(x, y, width, height);
/* 405 */     source.validate();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private int getDragDistance(int larger, int smaller, int snapSize)
/*     */   {
/* 412 */     int halfway = snapSize / 2;
/* 413 */     int drag = larger - smaller;
/* 414 */     drag += (drag < 0 ? -halfway : halfway);
/* 415 */     drag = drag / snapSize * snapSize;
/*     */     
/* 417 */     return drag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private int getDragBounded(int drag, int snapSize, int dimension, int minimum, int maximum)
/*     */   {
/* 424 */     while (dimension + drag < minimum) {
/* 425 */       drag += snapSize;
/*     */     }
/* 427 */     while (dimension + drag > maximum) {
/* 428 */       drag -= snapSize;
/*     */     }
/*     */     
/* 431 */     return drag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private Dimension getBoundingSize(Component source)
/*     */   {
/* 438 */     if ((source instanceof java.awt.Window)) {
/* 439 */       GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 440 */       Rectangle bounds = env.getMaximumWindowBounds();
/* 441 */       return new Dimension(bounds.width, bounds.height);
/*     */     }
/* 443 */     return source.getParent().getSize();
/*     */   }
/*     */ }


/* Location:              G:\Documents\Modded Network Launcher\launcher\release-1588376336366.jar!\com\gildedgames\launcher\util\ComponentResizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */