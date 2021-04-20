(ns reframeexample.views
  (:require
   [re-frame.core :as re-frame]
   [reframeexample.events :as events]
   [reframeexample.subs :as subs]))

(defn display-user [{:keys [id avatar email] first-name :first_name}]
  [:div.horizontal {:key id}
   [:img.pr-15 {:src avatar}]
   [:div
    [:h2 first-name]
    [:p  (str "(" email ")")]]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        loading (re-frame/subscribe [::subs/loading])
        users (re-frame/subscribe [::subs/users])]
    [:div
     [:h1
      "Hello from " @name]
     (when @loading "Loading...")
     (map display-user @users)
     [:button {:on-click #(re-frame/dispatch [::events/fetch-users])} "Make API Call"]
     [:button {:on-click #(re-frame/dispatch [::events/update-name "🥳"])} "Update Name"]]))
