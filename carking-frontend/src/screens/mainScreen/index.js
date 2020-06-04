import React, { useState, useEffect } from "react";

import MainPanel from "../../components/mainPanel";
import NewEmployeePanel from "../../components/newEmployeePanel";
import NewParkingPanel from "../../components/newParkingPanel";

import "./style.css";
import ProfilePanel from "../../components/profilePanel";
import UserIcon from "../../components/icons/userIcon";
import HomeIcon from "../../components/icons/homeIcon";
import PlusIcon from "../../components/icons/plusIcon";
import ProfileIcon from "../../components/icons/profileIcon";
import CogIcon from "../../components/icons/cogIcon";
import CarIcon from "../../components/icons/carIcon";
import MarkerIcon from "../../components/icons/markerIcon";
import ExitIcon from "../../components/icons/exitIcon";
import getInfoEmployeeLogged from "../../services/getInfoEmployeeLogged";
import { useHistory } from "react-router-dom";
import ParkingOutIcon from "../../components/icons/parkingOutIcon";
import ParkingOutPanel from "../../components/parkingOutPanel";

export default function MainScreen() {
  const history = useHistory();

  const [name, setName] = useState("");

  const components = [
    <MainPanel />,
    <NewEmployeePanel />,
    <NewParkingPanel />,
    <ProfilePanel />,
    <ParkingOutPanel />
  ];
  const [currentComponent, setCurrentComponent] = useState(4);

  const navigateToHome = () => {
    setCurrentComponent(0);
  };
  const navigateToRegisterFuncionario = () => {
    setCurrentComponent(1);
  };
  const navigateToRegisterParking = () => {
    setCurrentComponent(2);
  };
  const navigateToProfile = () => {
    setCurrentComponent(3);
  };
  const navigateToParkingOut = () => {
    setCurrentComponent(4);
  };
  const navigateToExit = () => {
    localStorage.removeItem("user");
    history.push("/");
  };

  const loadEmployeeName = async () => {
    const response = await getInfoEmployeeLogged();
    if (response) {
      setName(response.name.split(" ")[0]);
    }
  };

  useEffect(() => {
    loadEmployeeName();
  }, []);

  return (
    <div id="main-screen">
      <div id="menu">
        <div id="user" className="menuItem">
          <div id="user-icon" className="menu-icon">
            <UserIcon />
          </div>

          <div id="user-name" className="menu-name">
            <span>{name}</span>
          </div>
        </div>

        <div id="user-line">
          <hr />
        </div>

        <div id="home" className="menuItem" onClick={navigateToHome}>
          <div id="home-icon" className="menu-icon">
            {currentComponent === 0 && <MarkerIcon />}
            <HomeIcon />
          </div>

          <div id="home-name" className="menu-name">
            <span>Inicio</span>
          </div>
        </div>

        <div id="add" className="menuItem">
          <div id="add-header">
            <div id="add-icon" className="menu-icon">
              <PlusIcon />
            </div>

            <div id="add-name" className="menu-name">
              <span>Cadastrar</span>
            </div>
          </div>

          <div id="add-body">
            <div
              className="submenuItem"
              onClick={navigateToRegisterFuncionario}
            >
              <div id="employee-icon" className="submenu-icon">
                {currentComponent === 1 && <MarkerIcon />} <CogIcon />
              </div>

              <div id="employee-name" className="submenu-name">
                <span>Funcionário</span>
              </div>
            </div>

            <div className="submenuItem" onClick={navigateToRegisterParking}>
              <div id="car-icon" className="submenu-icon">
                {currentComponent === 2 && <MarkerIcon />}
                <CarIcon />
              </div>

              <div id="car-name" className="submenu-name">
                <span>Estacionamento</span>
              </div>
            </div>
          </div>
        </div>

        <div id="profile" className="menuItem" onClick={navigateToProfile}>
          <div id="profile-icon" className="menu-icon">
            {currentComponent === 3 && <MarkerIcon />}
            <ProfileIcon />
          </div>

          <div id="profile-name" className="menu-name">
            <span>Perfil</span>
          </div>
        </div>

        <div
          id="parking-out"
          className="menuItem"
          onClick={navigateToParkingOut}
        >
          <div id="parking-out-icon" className="menu-icon">
            {currentComponent === 4 && <MarkerIcon />}
            <ParkingOutIcon />
          </div>

          <div id="parking-out-name" className="menu-name">
            <span>Liberar</span>
          </div>
        </div>

        <div id="exit" className="menuItem" onClick={navigateToExit}>
          <div id="exit-icon" className="menu-icon">
            <ExitIcon />
          </div>

          <div id="exit-name" className="menu-name">
            <span>Sair</span>
          </div>
        </div>
      </div>

      <div id="component">{components[currentComponent]}</div>
    </div>
  );
}
