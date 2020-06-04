import api from "./api";

export default async function getEntranceDriverParking() {
  try {
    const result = await api().get("/driver/entrance");
    return result.data;
  } catch (err) {
    console.log(err);
    return [];
  }
}
