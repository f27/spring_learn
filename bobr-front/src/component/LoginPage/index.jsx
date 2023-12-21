import {Link} from "react-router-dom";
import {generateCodeChallenge, generateCodeVerifier} from "../../utils/pkce";

export const LoginPage = () => {

    const verifier = generateCodeVerifier();
    sessionStorage.setItem('codeVerifier', verifier);
    const codeChallenge = generateCodeChallenge();
    sessionStorage.setItem('codeChallenge', codeChallenge);

    return (
      <>
          <div className={"content"}>
              <div className={"form"}>
                  <div className={"main__header"}>
                      <h1 >Welcome to Bobr</h1>
                      <h2>Automate your processes</h2>
                  </div>
                  <img className={"main__logo"} src="/images/bobr-logo.png" width={150}
                       height={167} alt="Bobr kurwa"></img>
                  <div className={"main__links"}>
                      <Link className={"main__link"} to={'/redirect'}>Login</Link>
                      <a className={"main__link"} href={`${process.env.REACT_APP_AUTH_URL}/register`}>Register</a>
                  </div>
              </div>
          </div>
      </>
    );
}