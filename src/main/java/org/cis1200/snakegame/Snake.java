package org.cis1200.snakegame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.cis1200.snakegame.savehelpers.SnakeCellState;

import java.awt.Color;

public class Snake extends GameEntity implements Movable {
    private int headRow;
    private int headCol;
    private Direction[][] directions;
    private int tailRow;
    private int tailCol;
    private boolean nextMoveShouldGrowSnake = false;
    private BufferedImage bodyImage;
    private BufferedImage headImageUp;
    private BufferedImage headImageDown;
    private BufferedImage headImageLeft;
    private BufferedImage headImageRight;

    public Snake() {
        super();
        reset();

        setOpaque(false);
        setPreferredSize(new Dimension(400, 400));

        try {
            headImageUp = ImageIO.read(new File("res/snakehead-u.png"));
            headImageDown = ImageIO.read(new File("res/snakehead-d.png"));
            headImageLeft = ImageIO.read(new File("res/snakehead-l.png"));
            headImageRight = ImageIO.read(new File("res/snakehead-r.png"));
            bodyImage = ImageIO.read(new File("res/snakebody.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 0; i < this.getPositions().length; i++) {
            for (int j = 0; j < this.getPositions()[i].length; j++) {
                if (this.getPositions()[i][j]) {
                    int y = i * 20;
                    int x = j * 20;

                    if (i == headRow && j == headCol) {
                        Direction headDirection = directions[headRow][headCol];
                        BufferedImage headImage = null;
                        switch (headDirection) {
                            case UP: {
                                headImage = headImageUp;
                                break;
                            }
                            case DOWN: {
                                headImage = headImageDown;
                                break;
                            }
                            case LEFT: {
                                headImage = headImageLeft;
                                break;
                            }
                            case RIGHT: {
                                headImage = headImageRight;
                                break;
                            }
                            default:
                                break;
                        }
                        g.drawImage(headImage, x, y, 20, 20, null);
                    } else {
                        g.drawImage(bodyImage, x, y, 20, 20, null);
                    }
                }
            }
        }
        // addDebugMarkings(g);
    }

    private void addDebugMarkings(Graphics g) {
        for (int i = 0; i < directions.length; i++) {
            for (int j = 0; j < directions[i].length; j++) {
                Direction cellDirection = directions[i][j];
                if (cellDirection != null) {
                    g.setColor(Color.BLACK);
                    int y = i * 20;
                    int x = j * 20;
                    if (cellDirection == Direction.UP) {
                        g.fillRect(x, y, 20, 4); // Top border
                    } else if (cellDirection == Direction.DOWN) {
                        g.fillRect(x, y + 16, 20, 4); // Bottom border
                    } else if (cellDirection == Direction.LEFT) {
                        g.fillRect(x, y, 4, 20); // Left border
                    } else if (cellDirection == Direction.RIGHT) {
                        g.fillRect(x + 16, y, 4, 20); // Right border
                    }
                }
            }
        }
    }

    public void move(GameEntity[] gameEntities) {
        if (directions[headRow][headCol] != null) {

            // move the tail
            if (!nextMoveShouldGrowSnake) {
                this.getPositions()[tailRow][tailCol] = false;
                Direction tailDirection = directions[tailRow][tailCol];
                directions[tailRow][tailCol] = null;

                switch (tailDirection) {
                    case UP: {
                        tailRow--;
                        break;
                    }
                    case DOWN: {
                        tailRow++;
                        break;
                    }
                    case LEFT: {
                        tailCol--;
                        break;
                    }
                    case RIGHT: {
                        tailCol++;
                        break;
                    }
                    default:
                        break;
                }
            }
            nextMoveShouldGrowSnake = false;

            // move the head
            Direction headDirection = directions[headRow][headCol];
            switch (headDirection) {
                case UP: {
                    headRow--;
                    break;
                }
                case DOWN: {
                    headRow++;
                    break;
                }
                case LEFT: {
                    headCol--;
                    break;
                }
                case RIGHT: {
                    headCol++;
                    break;
                }
                default:
                    break;
            }
            // move the head
            this.getPositions()[headRow][headCol] = true;
            directions[headRow][headCol] = headDirection;

            repaint();
        }
    }

    public void setDirection(Direction d) {
        switch (d) {
            case UP: {
                if (this.directions[headRow][headCol] == Direction.DOWN) {
                    return;
                }
                break;
            }
            case DOWN: {
                if (this.directions[headRow][headCol] == Direction.UP) {
                    return;
                }
                break;
            }
            case LEFT: {
                if (this.directions[headRow][headCol] == Direction.RIGHT) {
                    return;
                }
                break;
            }
            case RIGHT: {
                if (this.directions[headRow][headCol] == Direction.LEFT) {
                    return;
                }
                break;
            }
            default:
                break;
        }
        this.directions[headRow][headCol] = d;
        repaint();
    }

    @Override
    public void handleCollision(Collidable other) {
        if (other instanceof Fruit) {
            // grow snake body
            nextMoveShouldGrowSnake = true;
        }
    }

    @Override
    public boolean[][] computeFuturePositions() {
        // create a new 2D boolean array and copy the old one
        boolean[][] nextPositions = new boolean[20][20];
        for (int i = 0; i < getPositions().length; i++) {
            System.arraycopy(getPositions()[i], 0, nextPositions[i], 0, getPositions()[i].length);
        }
        // move the head on the local copy based on the direction
        if (directions[headRow][headCol] != null) {
            Direction headDirection = directions[headRow][headCol];
            switch (headDirection) {
                case UP: {
                    nextPositions[headRow - 1][headCol] = true;
                    break;
                }
                case DOWN: {
                    nextPositions[headRow + 1][headCol] = true;
                    break;
                }
                case LEFT: {
                    nextPositions[headRow][headCol - 1] = true;
                    break;
                }
                case RIGHT: {
                    nextPositions[headRow][headCol + 1] = true;
                    break;
                }
                default:
                    break;
            }
        }
        return nextPositions;
    }

    @Override
    public boolean willCollideWith(Collidable other) {
        if (other instanceof Snake) {
            // compute tempHeadRow and tempHeadCol based on direction
            int tempHeadRow = headRow;
            int tempHeadCol = headCol;
            if (directions[headRow][headCol] != null) {
                Direction headDirection = directions[headRow][headCol];
                switch (headDirection) {
                    case UP: {
                        tempHeadRow--;
                        break;
                    }
                    case DOWN: {
                        tempHeadRow++;
                        break;
                    }
                    case LEFT: {
                        tempHeadCol--;
                        break;
                    }
                    case RIGHT: {
                        tempHeadCol++;
                        break;
                    }
                    default:
                        break;
                }
            }
            Snake otherSnake = (Snake) other;
            if (otherSnake.getPositions()[tempHeadRow][tempHeadCol]
                    && !(tempHeadRow == tailRow && tempHeadCol == tailCol)) {
                return true;
            }
            return false;
        } else {
            return super.willCollideWith(other);
        }
    }

    public List<SnakeCellState> getCells() {
        List<SnakeCellState> activeCells = new ArrayList<>();
        for (int i = 0; i < getPositions().length; i++) {
            for (int j = 0; j < getPositions()[i].length; j++) {
                if (getPositions()[i][j]) {
                    activeCells.add(new SnakeCellState(List.of(i, j), directions[i][j].toString()));
                }
            }
        }
        return activeCells;
    }

    public List<Integer> getHeadRowCol() {
        List<Integer> headRowCol = new ArrayList<>();
        headRowCol.add(headRow);
        headRowCol.add(headCol);
        return headRowCol;
    }

    public List<Integer> getTailRowCol() {
        List<Integer> tailRowCol = new ArrayList<>();
        tailRowCol.add(tailRow);
        tailRowCol.add(tailCol);
        return tailRowCol;
    }

    public void setBody(List<SnakeCellState> cells) {
        setPositions(new boolean[20][20]);
        this.directions = new Direction[20][20];
        for (SnakeCellState cell : cells) {
            List<Integer> rowCol = cell.position();
            int row = rowCol.get(0);
            int col = rowCol.get(1);
            getPositions()[row][col] = true;
            this.directions[row][col] = Direction.valueOf(cell.direction());
        }
        repaint();
    }

    public Direction getDirection() {
        return directions[headRow][headCol];
    }

    public void reset() {
        setPositions(new boolean[20][20]);
        headRow = 5;
        headCol = 5;
        getPositions()[5][5] = true;
        getPositions()[4][5] = true;
        getPositions()[3][5] = true;
        getPositions()[2][5] = true;
        tailRow = 2;
        tailCol = 5;
        directions = new Direction[20][20];
        directions[5][5] = Direction.DOWN;
        directions[4][5] = Direction.DOWN;
        directions[3][5] = Direction.DOWN;
        directions[2][5] = Direction.DOWN;
        nextMoveShouldGrowSnake = false;
        repaint();
    }

    public void setHeadRowCol(List<Integer> head) {
        this.headRow = head.get(0);
        this.headCol = head.get(1);
    }

    public void setTailRowCol(List<Integer> tail) {
        this.tailRow = tail.get(0);
        this.tailCol = tail.get(1);
    }
}
