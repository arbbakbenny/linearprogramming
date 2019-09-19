(ns linearprogramming.core
  (:use [uncomplicate.neanderthal core native math linalg]
        [uncomplicate.fluokitten.core]
        [clojure.core.matrix :only [identity-matrix]]
        )
)

(def c (dv 80 60))

(def A (dge 3 2  [1 3 1 2 1 0]))

(def b (dv 42000 50000 15000))

(defn initialize
  "returns expanded matrix of the initial problem with identity matrix"
  [matrix]
  (let [
        rows (mrows matrix)
        columns (ncols matrix)
        total (+ rows columns)
        tm1 (- total 1)
        initializedEmpty (dge rows (+ rows columns))
        identity-matrix (dge rows rows (identity-matrix rows))
        ]
    (axpy! matrix (submatrix initializedEmpty 0 0 rows columns))
    (axpy! identity-matrix (submatrix initializedEmpty 0 columns rows rows))
    initializedEmpty
    )
  )

(defn simplex
  [c                                                        ;; coefficients from target function
   A                                                        ;; matrix with limitations coefficients
   b                                                        ;; limitation values
   ]
  (let [
        initialA (initialize A)
        identity-matrix (dge 3 3 [1 0 0 0 1 0 0 0 1])
        initial-values-of-dummy-variables b
        sol (sv identity-matrix A)
        target-function-value (mv (trans sol) (dv 0 0 0))
        max-diff (axpy c (scal -1 target-function-value))
        index-of-vector-to-enter (imax max-diff)
        to-enter-vector (col sol index-of-vector-to-enter)
        divide (fmap (fn ^double [^double el] (/ 1 el)) to-enter-vector)
        result (fmap *
                 initial-values-of-dummy-variables
                 divide
                 )
        index-of-vector-to-leave (imin result)
        ]
    (print to-enter-vector divide result index-of-vector-to-leave)
    )

  )

(simplex c A b)

