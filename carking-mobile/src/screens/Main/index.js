import React, { useState } from "react";
import "./style.css";
import EntranceParkings from "../../components/EntranceParkings";
import TakeOffParkings from "../../components/TakeOffParkings";

const tabs = [<EntranceParkings />, <TakeOffParkings />];

export default function Main() {
  const [currentTab, setCurrentTab] = useState(0);

  const changeTabEntrance = () => {
    setCurrentTab(0);
  };

  const changeTabTakeoff = () => {
    setCurrentTab(1);
  };

  return (
    <main>
      <link
        href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap"
        rel="stylesheet"
      />
      <header>
        <h1 id="logotxt">Driver App</h1>
      </header>
      <section id="container-preto">
        <div id="container-logo">
          <img id="logo" src="https://i.imgur.com/Z7fOriF.png" alt="logo" />
        </div>

        <div id="menustoggle">
          <input
            id="toggle-on"
            class="toggle toggle-left"
            name="toggle"
            value="false"
            type="radio"
            checked
          />

          <label for="toggle-on" class="btn" onClick={changeTabEntrance}>
            Entrada
          </label>

          <input
            id="toggle-off"
            class="toggle toggle-right"
            name="toggle"
            value="true"
            type="radio"
          />

          <label for="toggle-off" class="btn" onClick={changeTabTakeoff}>
            Retirada
          </label>
        </div>

        <div id="todolist">
          <div id="card-space">{tabs[currentTab]}</div>
        </div>
      </section>
    </main>
  );
}
