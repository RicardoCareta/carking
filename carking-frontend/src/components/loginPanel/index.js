import React, { useState } from "react";
import "./style.css";
import login from "../../services/login";
import { useHistory } from "react-router-dom";

function LoginPanel() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const history = useHistory();

  const loginEventHandler = async () => {
    //history.push("/main");

    const result = await login(username, password);
    if (result) {
      history.push("/main");
    } else {
      alert("Usuário e senha estão incorretos");
    }
  };

  return (
    <div id="login-panel">
      <link
        href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap"
        rel="stylesheet"
      />
      <img
        src="https://i.imgur.com/LuR0fL1.png"
        alt="Logo"
        title="Carkin' Logo"
        id="logo"
      />
      <div id="login-form">
        <form id="login">
          <input
            type="text"
            value={username}
            onChange={ev => setUsername(ev.target.value)}
            id="user-field"
            placeholder="Usuário"
          />
          <input
            type="password"
            value={password}
            onChange={ev => setPassword(ev.target.value)}
            id="pass-field"
            placeholder="Senha"
          />
          <input
            type="button"
            value="ENTRAR"
            id="btn-in"
            onClick={loginEventHandler}
          />
        </form>
      </div>
    </div>
  );
}

export default LoginPanel;
