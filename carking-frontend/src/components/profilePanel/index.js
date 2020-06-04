import React, { useState, useEffect } from "react";
import "./style.css";
import UpdateEmployee from "../../services/updateEmployee";
import getInfoEmployeeLogged from "../../services/getInfoEmployeeLogged";
import { useHistory } from "react-router-dom";

function ProfilePanel() {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const history = useHistory();

  const updateEmployeeClickHandler = async () => {
    const result = await UpdateEmployee(name, username, password);
    if (result && result !== -1) {
      alert(
        "Dados alterados com sucesso.\nAgora será necessário relogar para as alterações entrarem em vigor."
      );
      localStorage.removeItem("user");
      history.push("/");
    } else if (result === -1) {
      alert("Um empregado com este usuário já existe");
    } else {
      alert("Ocorreu um erro");
    }
  };

  const loadEmployeeInfo = async () => {
    const employee = await getInfoEmployeeLogged();
    if (employee) {
      setName(employee.name);
      setUsername(employee.username);
      setPassword(employee.password);
    }
  };

  useEffect(() => {
    loadEmployeeInfo();
  }, []);

  return (
    <div id="profile-panel">
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
      <div id="profile-form">
        <form id="profile">
          <input
            type="text"
            id="name-field"
            placeholder="Nome"
            value={name}
            onChange={ev => setName(ev.target.value)}
          />
          <input
            type="text"
            id="username-field"
            placeholder="Usuário"
            value={username}
            onChange={ev => setUsername(ev.target.value)}
          />
          <input
            type="password"
            id="password-field"
            placeholder="Senha"
            value={password}
            onChange={ev => setPassword(ev.target.value)}
          />
          <div id="button-div-save-profile">
            <input
              type="button"
              value="SALVAR"
              id="btn-in"
              onClick={updateEmployeeClickHandler}
            />
          </div>
        </form>
      </div>
    </div>
  );
}

export default ProfilePanel;
