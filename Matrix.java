/**
 * A simple m x n matrix class. 
 * 
 * @author Dylan Moreno
 * @version 3/9/2022
 *
 * This work complies with JMU Honor Code.
 */

public class Matrix {
  
  private int m, n;
  private double[][] M;
  
  public Matrix(double[][] array) {
    M = array;
    m = array.length; 
    n = array[0].length; 
  }
  
  /**
   * @return The number of columns in the matrix. 
   */
  public int nCols() { return n; }
  
  /** 
   * @return the number of rows.
   */ 
  public int nRows() { return m; }
  
  /** 
   * @param i 
   * @param j
   * @return The entry at row i column j. 
   */
  public double entry(int i, int j) { return M[i][j]; }
  
  /**
   * Computes the dot product of this matrix with the parameter that. (Return value is this . that)
   * Recall that the dot product is the typical matrix multiplication. 
   * @param that The matrix to apply this matrix to. 
   * @throws BadDimensionException If this.nCols() != that.nRows() because the dot product is not defined
   * @return The dot product of this matrix with that. 
   */
  public Matrix dot(Matrix that) throws UndefinedMatrixOpException {
    
    // Check for bad dimensions (dot product is not defined)
    if (this.nCols() != that.nRows()) {
      throw new UndefinedMatrixOpException("The two matrices have different dimensions.", this, that);
    }
     
    // Create dot matrix
    double[][] dot = new double[this.nRows()][that.nCols()];
    
    // Populate dot matrix
    for (int i = 0; i < dot.length; i++) {
      for (int j = 0; j < dot[i].length; j++) {
        for (int k = 0; k < this.nCols(); k++) {
          dot[i][j] += this.entry(i, k) * that.entry(k, j);
        }
      }
    }
    
    return new Matrix(dot);
    
  }
  
  /**
   * Add this matrix to that and returns the result. (Return value is this + that)
   * @param that the matrix to add this matrix to. 
   * @throws BadDimensionException If the dimension of the two matrices are not identical. 
   * @return The sum of the this and that. 
   */
   public Matrix plus(Matrix that) throws UndefinedMatrixOpException {
     
     // Check for bad dimensions
     if (this.nCols() != that.nCols() || this.nRows() != that.nRows()) {
       throw new UndefinedMatrixOpException("The two matrices have different dimensions.", this, that);
     }
     
     // Create plus matrix
     double[][] plus = new double[m][n];
     
     // Populate plus matrix
     for (int r = 0; r < M.length; r++) {
       for (int c = 0; c < M[0].length; c++) {
         
         plus[r][c] = this.entry(r, c) + that.entry(r, c);
         
       }
     }
     
     return new Matrix(plus);
   }
   
   /**
    * @param theta The rotation angle. 
    * @return The homogeneous rotation matrix for a given value for theta. 
    */ 
   public static Matrix rotationH2D(double theta) {
     double[][] R = {{Math.cos(theta), -Math.sin(theta), 0}, {Math.sin(theta), Math.cos(theta), 0}, {0, 0, 1}};
     return new Matrix(R);
   }
   
   /**
    * @param tx The amount to translate in the x direction. 
    * @param ty The amount to translate in the y direction. 
    * @return The matrix representing a translation of tx, ty. 
    */
   public static Matrix translationH2D(double tx, double ty) {
     double[][] R = {{1, 0, tx}, {0, 1, ty}, {0, 0, 1}};
     return new Matrix(R);
   }
   
   /**
    * @param x The x coordinate
    * @param y The y coordinate
      * @return The column matrix representing in homogeneous coordinates the point (x, y). 
    */
   public static Matrix vectorH2D(double x, double y) {
     double[][] R = {{x, 0, 0}, {0, y, 0}, {0, 0, 1}};
     return new Matrix(R);
   }
   
   /** 
    * @param n The dimension of the matrix. Recall that the identity matrix has 1's for any entry that is in the same row index as its column index, 0's everywhere else. 
    * @return the nxn identity matrix
    */
   public static Matrix identity(int n) { return identity(n, n); }
   
   /**
    * Computes the mxn identity matrix which has 1's for every entry at the same row and column index and 
    * 0 for all other entries. 
    * @param m
    * @param n 
    * @return the mxn identity matrix. 
    */
    public static Matrix identity(int m, int n) {
      
      double R[][] = new double[m][n];
      
      for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
          if (r == c) {
            R[r][c] = 1;
          } else {
            R[r][c] = 0;
          }
        }
      }
      
      return new Matrix(R);
    }
    
    /** 
     * A little helpful toString() in case you want to print your matrix to System.out
     */
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          sb.append(M[i][j]);
          sb.append('\t');
        }
        sb.append('\n');
      }
      return sb.toString();
    }
}
