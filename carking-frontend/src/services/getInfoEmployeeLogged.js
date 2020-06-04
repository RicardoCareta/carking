import api from "./api";

export default async function getInfoEmployeeLogged() {
  try {
    const result = await api().get("/employee/info");
    return result.data;
  } catch (err) {
    console.log(err);
    return null;
  }
}
