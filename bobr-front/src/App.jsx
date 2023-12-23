import {BrowserRouter, Route, Routes} from "react-router-dom";
import {LoginPage} from "./component/LoginPage";
import {Redirect} from "./component/Redirect";
import {ProtectedRoutes} from "./component/ProtectedRoutes";
import {MainLayout} from "./component/MainLayout";
import {useState} from "react";
import {UserContext} from "./context/UserContext";

function App() {

    const [user, setUser] = useState(null);
    const [userLoading, setUserLoading] = useState(true);
    const userContext = {user, setUser};

    return (
        <div className="App">
            <BrowserRouter>
                <UserContext.Provider value={userContext}>
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
                </UserContext.Provider>
            </BrowserRouter>
        </div>
    );
}

export default App;
