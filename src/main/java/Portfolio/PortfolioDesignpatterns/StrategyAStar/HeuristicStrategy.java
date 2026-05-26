package Portfolio.PortfolioDesignpatterns.StrategyAStar;

import graphs.astar.MazeNode;

@FunctionalInterface
public interface HeuristicStrategy {
    int calculate(MazeNode node, MazeNode destination);
}