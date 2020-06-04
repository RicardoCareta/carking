import api from "./api";

export default async function UpdateEmployee(name, username, password) {
  try {
    await api().patch("/employee", {
      name,
      username,
      password
    });
    return true;
  } catch (err) {
    console.log(err);

    if (err.response) {
      if (err.response.status === 418) {
        //User exists
        return -1;
      }
    }
    return false;
  }
}
