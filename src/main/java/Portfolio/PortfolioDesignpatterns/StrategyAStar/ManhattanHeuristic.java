package Portfolio.PortfolioDesignpatterns.StrategyAStar;

import graphs.astar.MazeNode;

// Manhattan — god til gitterbaserede labyrinter (ingen diagonal bevægelse)
public class ManhattanHeuristic implements HeuristicStrategy {
    @Override
    public int calculate(MazeNode node, MazeNode destination) {
        return Math.abs(destination.getRow() - node.getRow())
                + Math.abs(destination.getCol() - node.getCol());
    }
}