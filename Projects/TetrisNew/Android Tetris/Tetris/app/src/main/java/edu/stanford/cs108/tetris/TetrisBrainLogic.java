package edu.stanford.cs108.tetris;


public class TetrisBrainLogic extends TetrisLogic {

    private DefaultBrain brain = new DefaultBrain();
    private Brain.Move bestMove;
    protected boolean brainOn = false;

    public TetrisBrainLogic(TetrisUIInterface uiInterface) {
        super(uiInterface);
    }

    public void setBrainMode(boolean brain){ brainOn = brain; }

    @Override
    protected void tick(int verb) {

        if (brainOn && verb == DOWN) {
            board.undo(); // remove from its old position

            bestMove = brain.bestMove(board, currentPiece, HEIGHT, null);

            if (bestMove != null) {
                if (currentPiece != null) {
                    uiInterface.boardUpdated();
                }


                if (!currentPiece.equals(bestMove.piece)) {
                    super.tick(ROTATE);
                }

                if (bestMove.x > currentX) {
                    super.tick(RIGHT);
                } else if (bestMove.x < currentX) {
                    super.tick(LEFT);
                }

            }

            uiInterface.boardUpdated();
        }

        super.tick(verb);

    }

}
