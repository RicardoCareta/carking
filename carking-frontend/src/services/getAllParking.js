import api from "./api";

export default async function getAllParking() {
  try {
    return (await api().get("/parking")).data;
  } catch (err) {
    console.log(err);
  }
}
