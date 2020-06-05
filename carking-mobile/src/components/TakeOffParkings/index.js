import React, { useEffect, useState } from "react";
import Card from "../Card";
import getTakeOffDriverParking from "../../services/getTakeOffDriverParking";
import patchConfirmDriverParking from "../../services/patchConfirmDriverParking";
import Modal from "../Modal";

export default function TakeOffParkings() {
  const [listTakeOff, setListTakeOff] = useState([]);

  const [openModal, setOpenModal] = useState(false);
  const [openedId, setOpenedId] = useState("");

  const confirmCallBack = async () => {
    const result = await patchConfirmDriverParking("takeoff", openedId);
    if (result) {
      alert("Confirmado com sucesso");
      setOpenModal(false);
      loadTakeOff();
    } else {
      alert("Ocorreu um erro");
    }
  };

  const cancelCallBack = () => {
    setOpenModal(false);
    setOpenedId("");
  };

  const selectParkingId = id => {
    setOpenedId(id);
    setOpenModal(true);
  };

  const loadTakeOff = async () => {
    setListTakeOff(await getTakeOffDriverParking());
  };

  useEffect(() => {
    loadTakeOff();
  }, []);
  return (
    <div>
      <Modal
        message="Confirmar veículo entregue?"
        open={openModal}
        confirmCallBack={confirmCallBack}
        cancelCallBack={cancelCallBack}
      />
      {listTakeOff.map(takeoff => (
        <Card
          callBackClick={() => selectParkingId(takeoff.id)}
          isEntrance={false}
          id={takeoff.id}
          model={takeoff.vehicle.model}
          brand={takeoff.vehicle.brand}
          plate={takeoff.vehicle.plate}
          color={takeoff.vehicle.color}
          name={takeoff.customerDTO.name}
          parkingPlace={takeoff.parkingPlace}
        />
      ))}
    </div>
  );
}
