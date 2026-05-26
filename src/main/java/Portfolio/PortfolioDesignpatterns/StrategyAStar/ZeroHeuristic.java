package Portfolio.PortfolioDesignpatterns.StrategyAStar;

import graphs.astar.MazeNode;

// Zero — heuristikken returnerer altid 0, hvilket gør A* til Dijkstra
// Finder stadig den korteste vej, men udforsker langt flere noder
public class ZeroHeuristic implements HeuristicStrategy {
    @Override
    public int calculate(MazeNode node, MazeNode destination) {
        return 0;
    }
}