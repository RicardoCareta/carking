import React from "react";
import { Switch, Route, BrowserRouter as Router } from "react-router-dom";
import LoginScreen from "./screens/loginScreen";
import PrivateRoute from "./util/privateRoute";
import MainScreen from "./screens/mainScreen";

export default function Routes() {
  return (
    <Router>
      <Switch>
        <Route path="/" exact>
          <LoginScreen />
        </Route>

        <PrivateRoute path="/main" exact>
          <MainScreen />
        </PrivateRoute>
      </Switch>
    </Router>
  );
}
