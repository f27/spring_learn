import {BrowserRouter, Route, Routes} from "react-router-dom";
import {LoginPage} from "./component/LoginPage";
import {Redirect} from "./component/Redirect";
import {ProtectedRoutes} from "./component/ProtectedRoutes";
import {MainLayout} from "./component/MainLayout";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/redirect" element={<Redirect/>}/>
          <Route path="/authorized" element={<Redirect/>}/>
          <Route element={<ProtectedRoutes/>}>
            {/*<Route path="/profile" element={<Profile/>}/>*/}
            <Route path="/main" element={<MainLayout/>}/>
            {/*<Route path="/people" element={<PeopleLayout/>}/>*/}
            {/*<Route path="/friends" element={<FriendsLayout/>}/>*/}
          </Route>
          <Route path="*" element={<LoginPage/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
