package controller.players;

import controller.MainWindowController;
import model.GameState;
import model.Move;
import model.piece.Piece;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import static java.lang.Thread.yield;

public class Computer implements Player {

    static private ExecutorService threadPool = Executors.newCachedThreadPool();
    static private int DEPTH = 4;

    @Override
    public void onSquareClick(int x, int y) {
    }

    @Override
    public void onMove() {
        GameState gameState = MainWindowController.getInstance().getCurrentGameState();
        List<Future<Evaluation>> stateEvaluations = new LinkedList<>();
        for (Move move : gameState.getLegalMoves()) {
            GameState newGameState = gameState.clone();
            newGameState.makeAMove(move);
            stateEvaluations.add(threadPool.submit(new Evaluator(newGameState, MainWindowController.getInstance().getCurrentGameState().getCurrentPlayerColor(), DEPTH, move)));
        }
        Evaluation bestEvaluation = null;
        for (Future<Evaluation> stateEvaluation : stateEvaluations) {
            while (!stateEvaluation.isDone()) yield();
            try {
                if (bestEvaluation == null || stateEvaluation.get().evaluation > bestEvaluation.evaluation) {
                    bestEvaluation = stateEvaluation.get();
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            } catch (ExecutionException e) {
                System.out.println(e);
            }
        }
        gameState.makeAMove(bestEvaluation.nextMove);
        MainWindowController.getInstance().onMove();
    }

    static class Evaluation {
        public final int evaluation;
        public final Move nextMove;

        public Evaluation(int evaluation, Move nextMove) {
            this.evaluation = evaluation;
            this.nextMove = nextMove;
        }
    }


    static class Evaluator implements Callable<Evaluation> {

        private GameState gameState;
        private Piece.Color playerColor;
        private Move nextMove;
        int depth;

        public Evaluator(GameState gameState, Piece.Color playerColor, int depth, Move nextMove) {
            this.gameState = gameState;
            this.playerColor = playerColor;
            this.depth = depth;
            this.nextMove = nextMove;
        }

        @Override
        public Evaluation call() {
            if (depth > 1 && gameState.getLegalMoves().size() != 0) {
                List<Future<Evaluation>> stateEvaluations = new LinkedList<>();
                for (Move move : gameState.getLegalMoves()) {
                    GameState newGameState = gameState.clone();
                    newGameState.makeAMove(move);
                    stateEvaluations.add(threadPool.submit(new Evaluator(newGameState, playerColor, depth - 1, move)));
                }
                if (gameState.getCurrentPlayerColor() == playerColor) {
                    Integer maxEvaluation = Integer.MIN_VALUE;
                    for (Future<Evaluation> stateEvaluation : stateEvaluations) {
                        while (!stateEvaluation.isDone()) yield();
                        try {
                            if (stateEvaluation.get().evaluation > maxEvaluation) {
                                maxEvaluation = stateEvaluation.get().evaluation;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    return new Evaluation(maxEvaluation, nextMove);
                } else {
                    Integer minEvaluation = Integer.MAX_VALUE;
                    for (Future<Evaluation> stateEvaluation : stateEvaluations) {
                        while (!stateEvaluation.isDone()) yield();
                        try {
                            if (minEvaluation == null || stateEvaluation.get().evaluation < minEvaluation) {
                                minEvaluation = stateEvaluation.get().evaluation;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    return new Evaluation(minEvaluation, nextMove);
                }
            } else if (gameState.getWinner() != null) {
                if (gameState.getWinner() == playerColor) return new Evaluation(Integer.MAX_VALUE, nextMove);
                else {
                    return new Evaluation(Integer.MIN_VALUE, nextMove);
                }
            } else {
                return new Evaluation(evaluateState(gameState), nextMove);
            }
        }

        private int evaluateState(GameState gameState) {
            int result = 0;
            for (Piece piece : gameState.getPieces()) {
                int color = piece.getColor() == playerColor ? 1 : -1;
                switch (piece.getPieceType()) {
                    case KNIGHT:
                        result += color * 3;
                        break;
                    case PAWN:
                        result += color;
                        break;
                    case BISHOP:
                        result += color * 3;
                        break;
                    case ROOK:
                        result += color * 5;
                        break;
                    case QUEEN:
                        result += color * 9;
                    case KING:
                        result += color * 500;
                }
            }
            return result;
        }
    }
}
