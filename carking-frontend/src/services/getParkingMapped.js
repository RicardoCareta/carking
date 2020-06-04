import api from "./api";

export default async function getParkingMapped() {
  try {
    return (await api().get("/parking/map")).data;
  } catch (err) {
    console.log(err);
  }
}
