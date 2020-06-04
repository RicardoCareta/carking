import React from "react";

import { BrowserRouter, Route, Switch } from "react-router-dom";
import Main from "./screens/Main";
import Login from "./screens/Login";

export default function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Login} />

        <Route path="/main" exact component={Main} />
      </Switch>
    </BrowserRouter>
  );
}
