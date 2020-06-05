import React, { useState, useEffect } from "react";
import Card from "../Card";
import getEntranceDriverParking from "../../services/getEntranceDriverParking";
import Modal from "../Modal";
import patchConfirmDriverParking from "../../services/patchConfirmDriverParking";

export default function EntranceParkings() {
  const [listEntrance, setListEntrance] = useState([]);

  const [openModal, setOpenModal] = useState(false);
  const [openedId, setOpenedId] = useState("");

  const confirmCallBack = async () => {
    const result = await patchConfirmDriverParking("entrance", openedId);
    if (result) {
      alert("Confirmado com sucesso");
      setOpenModal(false);
      loadEntrance();
    } else {
      alert("Ocorreu um erro");
    }
  };

  const cancelCallBack = () => {
    setOpenModal(false);
    setOpenedId("");
  };

  const loadEntrance = async () => {
    setListEntrance(await getEntranceDriverParking());
  };

  const selectParkingId = id => {
    setOpenedId(id);
    setOpenModal(true);
  };

  useEffect(() => {
    loadEntrance();
  }, []);
  return (
    <div>
      <Modal
        message="Confirmar veículo estacionado?"
        open={openModal}
        confirmCallBack={confirmCallBack}
        cancelCallBack={cancelCallBack}
      />
      {listEntrance.map(entrance => (
        <Card
          callBackClick={() => selectParkingId(entrance.id)}
          isEntrance={true}
          id={entrance.id}
          model={entrance.vehicle.model}
          brand={entrance.vehicle.brand}
          plate={entrance.vehicle.plate}
          color={entrance.vehicle.color}
          name={entrance.customerDTO.name}
          parkingPlace={entrance.parkingPlace}
        />
      ))}
    </div>
  );
}
