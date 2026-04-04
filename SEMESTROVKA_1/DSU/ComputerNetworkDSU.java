package DSU;

/**
 * Структура данных: Система непересекающихся множеств (DSU).
 * Контекст задачи: Управление компьютерной сетью.
 */
public class ComputerNetworkDSU {
    private int[] parent;
    private int[] rank;

    // Массив map нужен для поддержки операции УДАЛЕНИЯ (отключения ПК).
    private int[] map;
    private int nextPhysicalNode;

    /**
     * @param maxComputers максимальное количество компьютеров.
     * @param maxOperations максимальное число операций.
     */
    public ComputerNetworkDSU(int maxComputers, int maxOperations) {
        parent = new int[maxOperations];
        rank = new int[maxOperations];
        map = new int[maxComputers];
        nextPhysicalNode = 0;
    }

    public void makeSet(int x) {
        map[x] = nextPhysicalNode;
        parent[nextPhysicalNode] = nextPhysicalNode;
        rank[nextPhysicalNode] = 0;
        nextPhysicalNode++;
    }

    public int find(int x) {
        int physicalIndex = map[x];
        return findPhysical(physicalIndex);
    }

    private int findPhysical(int p) {
        if (parent[p] == p) {
            return p;
        }
        return parent[p] = findPhysical(parent[p]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public void delete(int x) {
        makeSet(x);
    }
}