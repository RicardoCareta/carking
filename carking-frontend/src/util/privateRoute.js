import React, { useEffect } from "react";
import { Route, useHistory } from "react-router-dom";

export default function PrivateRoute(props) {
  const history = useHistory();

  async function checkLoginUser() {
    if (!(await localStorage.getItem("user"))) {
      history.push("/");
    }
  }

  useEffect(() => {
    checkLoginUser();
  }, []);

  return <Route {...props} />;
}
