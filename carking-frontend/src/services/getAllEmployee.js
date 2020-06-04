import api from "./api";

export default async function getAllEmployee() {
  try {
    const response = await api().get("/employee");
    return response.data;
  } catch (err) {}
}
