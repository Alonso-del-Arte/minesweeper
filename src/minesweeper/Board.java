/*
 * Copyright (C) 2020 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package minesweeper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

/**
 * Represents a board with mines.
 * @author Alonso del Arte
 */
public class Board {
    
    private static final Position TOP_LEFT_CORNER = new Position(0, 0);
    
    private static final PositionStatus[] STATUS_VALUES 
            = PositionStatus.values();
    
    private final Position maxCorner;
    
    private final HashMap<Position, PositionStatus> statuses = new HashMap<>();
    
    private final HashMap<Position, Optional<Flag>> flags = new HashMap<>();
    
    private final HashMap<Position, Optional<Mine>> mines = new HashMap<>();
    
    private final HashMap<Position, Integer> neighborCounts = new HashMap<>();
    
    private final int mineCount;
    
    private int goodFlagCount = 0;
    
    private int wrongFlagCount = 0;
    
    private boolean gameOver = false;
    
    private boolean gameLost = false;
    
    public boolean gameUnderway() {
        return !this.gameOver;
    }
    
    public boolean gameWon() {
        return this.gameOver && !this.gameLost;
    }

    public PositionStatus query(Position position) {
        return this.statuses.get(position);
    }
    
    private void revealEmptySquare(Position position) {
        int neighborCount = this.neighborCounts.get(position);
        PositionStatus status;
        switch (neighborCount) {
            case 0:
                status = PositionStatus.REVEALED_EMPTY;
                break;
            case 1:
                status = PositionStatus.REVEALED_EMPTY_NEAR_1;
                break;
            case 2:
                status = PositionStatus.REVEALED_EMPTY_NEAR_2;
                break;
            case 3:
                    status = PositionStatus.REVEALED_EMPTY_NEAR_3;
                    break;
            case 4:
                status = PositionStatus.REVEALED_EMPTY_NEAR_4;
                break;
            case 5:
                status = PositionStatus.REVEALED_EMPTY_NEAR_5;
                break;
            case 6:
                status = PositionStatus.REVEALED_EMPTY_NEAR_6;
                break;
            case 7:
                status = PositionStatus.REVEALED_EMPTY_NEAR_7;
                break;
            case 8:
                status = PositionStatus.REVEALED_EMPTY_NEAR_8;
                break;
            default:
                String msg = "Neighbor count " + neighborCount 
                        + " is not valid";
                throw new RuntimeException(msg);
        }
        this.statuses.put(position, status);
    }
    
    private void revealEmptyNeighbors(Position initial) {
        HashSet<Position> positions = new HashSet<>();
        HashSet<Position> prevNeighbors = new HashSet<>();
        HashSet<Position> neighbors, specNeighbors;
        prevNeighbors.add(initial);
        int prevCount = 0;
        int currCount = 1;
        while (prevCount < currCount) {
            neighbors = new HashSet<>();
            for (Position position : prevNeighbors) {
                specNeighbors = position.getNeighbors(this.maxCorner);
                specNeighbors.removeAll(positions);
                for (Position neighbor : specNeighbors) {
                    if (this.mines.get(neighbor).isPresent()) {
                        specNeighbors.remove(neighbor);
                    } else {
                        if (this.neighborCounts.get(neighbor) == 0) {
                            neighbors.add(neighbor);
                        } else {
                            this.revealEmptySquare(neighbor);
                        }
                    }
                }
            }
            positions.addAll(neighbors);
            prevNeighbors = neighbors;
            prevCount = currCount;
            currCount = positions.size();
        }
        positions.remove(initial);
        positions.forEach((currPos) -> {
            this.statuses.put(currPos, PositionStatus.REVEALED_EMPTY);
        });
    }
    
    /**
     * Uncovers a position, potentially revealing a mine, but more hopefully 
     * revealing neighbor counts, or a large swath of adjacent empty squares. 
     * This is not a pure function, on account of the possible side effect of 
     * uncovering several additional positions.
     * @param position The position to uncover.
     * @return An <code>Optional</code> object that is either empty or it 
     * contains a <code>Mine</code> object matching <code>position</code>.
     */
    public Optional<Mine> reveal(Position position) {
        if (this.gameOver) {
            String excMsg = "Game over; can't reveal any positions";
            throw new IllegalStateException(excMsg);
        }
        PositionStatus status = this.statuses.get(position);
        if (!status.equals(PositionStatus.COVERED)) {
            String excMsg = "Can't reveal " + position.toString() 
                    + " because its status is " + status.toString();
            throw new IllegalStateException(excMsg);
        }
        Optional<Mine> option = this.mines.get(position);
        if (option.isPresent()) {
            option.get().detonate();
            this.statuses.put(position, PositionStatus.DETONATED);
            this.gameOver = true;
            this.gameLost = true;
        } else {
            int neighborCount = this.neighborCounts.get(position);
            status = STATUS_VALUES[neighborCount];
            this.statuses.put(position, status);
            if (neighborCount == 0) {
                revealEmptyNeighbors(position);
            }
        }
        return option;
    }
    
    private void checkIfWon() {
        if (this.goodFlagCount == this.mineCount && this.wrongFlagCount == 0) {
            this.gameOver = true;
        }
    }
    
    public void flag(Position position) {
        if (this.gameOver) {
            String excMsg = "Game over; can't flag any positions";
            throw new IllegalStateException(excMsg);
        }
        if (this.flags.get(position).isPresent()) {
            String excMsg = "Position " + position.toString() 
                    + " is already flagged";
            throw new IllegalStateException(excMsg);
        }
        boolean correctness = this.mines.get(position).isPresent();
        Flag flag = new Flag(position, correctness);
        Optional<Flag> option = Optional.of(flag);
        this.flags.put(position, option);
        this.statuses.put(position, PositionStatus.FLAGGED);
        if (correctness) {
            this.goodFlagCount++;
        } else {
            this.wrongFlagCount++;
        }
        this.checkIfWon();
    }
    
    public void unflag(Position position) {
        if (this.gameOver) {
            String excMsg = "Game over; can't unflag any positions";
            throw new IllegalStateException(excMsg);
        }
        Optional<Flag> option = this.flags.get(position);
        if (!option.isPresent()) {
            String excMsg = "Position " + position.toString() 
                    + " can't be unflagged because it's not currently flagged";
            throw new IllegalStateException(excMsg);
        }
        Flag flag = option.get();
        if (flag.isCorrect()) {
            this.goodFlagCount--;
        } else {
            this.wrongFlagCount--;
        }
        this.flags.put(position, Optional.empty());
        this.statuses.put(position, PositionStatus.COVERED);
        this.checkIfWon();
    }
    
    /**
     * Creates a new board.
     * @param numberOfMines How many mines the board should have. Preferably a 
     * positive zero, but 0 is allowed.
     * @param maxPosition The bottom right corner position.
     * @return A new board, with the mines chosen pseudorandomly.
     */
    public static Board makeBoard(int numberOfMines, Position maxPosition) {
        if (numberOfMines < 0) {
            String excMsg = "Number of mines " + numberOfMines
                    + " is not valid, should be at least 0";
            throw new IllegalArgumentException(excMsg);
        }
        int capacity = (maxPosition.getX() + 1) * (maxPosition.getY() + 1);
        if (numberOfMines > capacity) {
            String excMsg = "Can't make board with " + numberOfMines 
                    + " mines but capacity for only " + capacity;
            throw new IllegalArgumentException(excMsg);
        }
        HashSet<Position> mineLocations = new HashSet<>();
        Position mineLocation;
        while (mineLocations.size() < numberOfMines) {
            mineLocation = Position.random(maxPosition);
            mineLocations.add(mineLocation);
        }
        return new Board(maxPosition, mineLocations);
    }
    
    private static boolean anyOutOfBounds(Position maxPos, 
            HashSet<Position> positions) {
        boolean boundFlag = true;
        Iterator iter = positions.iterator();
        Position curr;
        while (boundFlag && iter.hasNext()) {
            curr = (Position) iter.next();
            boundFlag = curr.isWithinBounds(maxPos);
        }
        return !boundFlag;
    }
    
    private void setStatuses() {
        for (Position curr = TOP_LEFT_CORNER; 
                curr.isWithinBounds(this.maxCorner);
                curr = curr.nextColumnWithReset(this.maxCorner)) {
            this.statuses.put(curr, PositionStatus.COVERED);
        }
    }
    
    private void prepFieldForFlagging() {
        for (Position curr = TOP_LEFT_CORNER; 
                curr.isWithinBounds(this.maxCorner);
                curr = curr.nextColumnWithReset(this.maxCorner)) {
            this.flags.put(curr, Optional.empty());
        }
    }
    
    private void setMines(HashSet<Position> mineLocations) {
        mineLocations.forEach((position) -> {
            Mine mine = new Mine(position);
            this.mines.put(position, Optional.of(mine));
        });
        for (Position curr = TOP_LEFT_CORNER; 
                curr.isWithinBounds(this.maxCorner);
                curr = curr.nextColumnWithReset(this.maxCorner)) {
            if (!this.mines.containsKey(curr)) {
                this.mines.put(curr, Optional.empty());
            }
        }
    }
    
    private void countNeighbors() {
        int counter;
        for (Position curr = TOP_LEFT_CORNER; 
                curr.isWithinBounds(this.maxCorner);
                curr = curr.nextColumnWithReset(this.maxCorner)) {
            HashSet<Position> neighbors = curr.getNeighbors(this.maxCorner);
            counter = 0;
            // TODO: Evaluate "Can use functional operators" warning
            for (Position neighbor : neighbors) {
                if (this.mines.get(neighbor).isPresent()) {
                    counter++;
                }
            }
            this.neighborCounts.put(curr, counter);
        }
    }
    
    /**
     * Sole constructor.
     * @param maxPos The position of the bottom right corner.
     * @param mineLocations A set of locations where to place the mines.
     */
    Board(Position maxPos, HashSet<Position> mineLocations) {
        if (anyOutOfBounds(maxPos, mineLocations)) {
            String excMsg = "All mine locations should be within bounds";
            throw new IllegalArgumentException(excMsg);
        }
        this.mineCount = mineLocations.size();
        this.maxCorner = maxPos;
        this.setStatuses();
        this.prepFieldForFlagging();
        this.setMines(mineLocations);
        this.countNeighbors();
    }
    
}
