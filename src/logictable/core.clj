(ns logictable.core
  (:refer-clojure :exclude [==])
  (:use clojure.core.logic))

(defne non-adjacento [left right coll]
  ([_ _ []])
  ([_ _ [_]])
  ([_ _ [first second . rest]]
     (!= [first second] [left right])
     (!= [first second] [right left])
     (fresh [tail]
       (conso second rest tail)
       (non-adjacento left right tail))))

;; TODO in a seating context first and last could be considered adjacent
(defne adjacento [left right coll]
  ([_ _ [left right . rest]])
  ([_ _ [right left . rest]])
  ([_ _ [_ . rest]] (adjacento left right rest)))

; very slow in this context
(defn adjacent
  "Declares x and y as adjacent items in list z"
  [x y z]
  (fresh [a b]
         (appendo a (llist x y b) z)))

(defn n-lvars [n]
  (take n (repeatedly lvar)))

(def peeps
  [:rob :amelia :martin :susan :dick :russell :lola :bay :raul :babs :dave :rick :julia :dan :felicity :regina])

(def peeps-together
  [[:rob :amelia]
   [:amelia :martin]
   [:susan :dick]
   [:lola :susan]])

(def peeps-apart
  [[:russell :rob]
   [:lola :bay]
   [:martin :susan]
   [:martin :dick]])


;; looking for something like...
;; (run 2 [q] (== q (n-lvars (count peeps))) (everyg #(membero % q) peeps) (non-adjacento {:name :marg} {:name :bob} q) (nexto {:name :rob} {:name :delia} q))
