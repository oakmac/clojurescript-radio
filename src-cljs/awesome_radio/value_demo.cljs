(ns awesome-radio.value-demo
  "Demo of Persistent Data Structures"
  (:require
    [awesome-radio.util :as util]))

;;------------------------------------------------------------------------------
;; Persistent Data Structures have fast value comparison
;;------------------------------------------------------------------------------

(def person1-mom
  {:name
     {:first "Sally"
      :last "Doe"}
   :age 64})

(def person1
  {:name
     {:first "John"
      :last "Doe"}
   :age 28
   :hair-color "brown"
   :parents
     {:mom person1-mom
      :dad
        {:name
           {:first "James"
            :last "doe"}
         :age 66}}
   :pets
     [{:species "fish"
       :name "Carl the Fish"
       :gender "unknown"}
      {:species "dog"
       :name "Russel"
       :gender "male"}
      {:species "dog"
       :name "Randy"
       :gender "male"}]})

(def person2
  {:name
     {:first "John"
      :last "Doe"}
   :age 28
   :hair-color "brown"
   :parents
     {:mom
        {:name
           {:first "Sally"
            :last "Doe"}
         :age 64}
      :dad
       {:name
          {:first "James"
           :last "doe"}
        :age 66}}
   :pets
      [{:species "fish"
        :name "Carl the Fish"
        :gender "unknown"}
       {:species "dog"
        :name "Russel"
        :gender "male"}
       {:species "dog"
        :name "Randy"
        :gender "male"}]})

; (if (= person1 person2)
;   (util/js-log "Person 1 and Person 2 are the same!")
;   (util/js-log "Person 1 and Person 2 are NOT the same."))
