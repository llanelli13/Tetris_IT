class BlocT {
    private int[][] forme;
    private int couleur;

    public BlocT() {
        // Forme en T
        forme = new int[][] {
                { 0, 1, 0 },
                { 1, 1, 1 },
                { 0, 0, 0 }
        };
        couleur = 1;
    }

    // Getters pour accéder à la forme et à la couleur du bloc
    public int[][] getForme() {
        return forme;
    }

    public int getCouleur() {
        return couleur;
    }
}

class BlocL {
    private int[][] forme;
    private int couleur;

    public BlocL() {
        // Forme en L
        forme = new int[][] {
                { 0, 0, 1 },
                { 1, 1, 1 },
                { 0, 0, 0 }
        };
        couleur = 2;
    }

    // Getters pour accéder à la forme et à la couleur du bloc
    public int[][] getForme() {
        return forme;
    }

    public int getCouleur() {
        return couleur;
    }
}

class BlocJ {
    private int[][] forme;
    private int couleur;

    public BlocJ() {
        // Forme en J
        forme = new int[][] {
                { 1, 0, 0 },
                { 1, 1, 1 },
                { 0, 0, 0 }
        };
        couleur = 3;
    }

    // Getters pour accéder à la forme et à la couleur du bloc
    public int[][] getForme() {
        return forme;
    }

    public int getCouleur() {
        return couleur;
    }
}

class BlocI {
    private int[][] forme;
    private int couleur;

    public BlocI() {
        // Forme en I
        forme = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        couleur = 4;
    }

    // Getters pour accéder à la forme et à la couleur du bloc
    public int[][] getForme() {
        return forme;
    }

    public int getCouleur() {
        return couleur;
    }
}

class BlocO {
    private int[][] forme;
    private int couleur;

    public BlocO() {
        // Forme en O
        forme = new int[][] {
                { 1, 1 },
                { 1, 1 }
        };
        couleur = 5;
    }

    // Getters pour accéder à la forme et à la couleur du bloc
    public int[][] getForme() {
        return forme;
    }

    public int getCouleur() {
        return couleur;
    }
}

class BlocS {
    private int[][] forme;
    private int couleur;

    public BlocS() {
        // Forme en S
        forme = new int[][] {
                { 0, 1, 1 },
                { 1, 1, 0 },
                { 0, 0, 0 }
        };
        couleur = 6;
    }

    // Getters pour accéder à la forme et à la couleur du bloc
    public int[][] getForme() {
        return forme;
    }

    public int getCouleur() {
        return couleur;
    }
}

class BlocZ {
    private int[][] forme;
    private int couleur;

    public BlocZ() {
        // Forme en Z
        forme = new int[][] {
                { 1, 1, 0 },
                { 0, 1, 1 },
                { 0, 0, 0 }
        };
        couleur = 7;
    }

    // Getters pour accéder à la forme et à la couleur du bloc
    public int[][] getForme() {
        return forme;
    }

    public int getCouleur() {
        return couleur;
    }
}
