import {useNavigate, useSearchParams} from "react-router-dom";
import {useEffect} from "react";

export const Redirect = () => {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();


    useEffect(() => {
        if (searchParams?.get('code')) {
            const code = searchParams?.get('code');


            const verifier = sessionStorage.getItem('codeVerifier');
            const initialUrl = `${process.env.REACT_APP_AUTH_URL}/oauth2/token?client_id=client&redirect_uri=${process.env.REACT_APP_FRONT_URL}/authorized&grant_type=authorization_code`;
            const url = `${initialUrl}&code=${code}&code_verifier=${verifier}`;
            const headers = new Headers();
            headers.append('Content-type', 'application/json');

            fetch(url, {
                method: 'POST',
                mode: 'cors',
                headers
            }).then(async (response) => {
                const token = await response.json();
                if(token?.id_token && token?.access_token) {
                    sessionStorage.setItem('id_token', token.id_token);
                    sessionStorage.setItem('access_token', token.access_token);
                    sessionStorage.removeItem("codeChallenge");
                    sessionStorage.removeItem("codeVerifier");
                    navigate('/main');
                }

            }).catch((err) => {
                console.log(err);
                navigate('/');
            })
        }
        // eslint-disable-next-line
    }, []);

    useEffect(() => {
        if (!searchParams?.get('code')) {
            const codeChallenge = sessionStorage.getItem('codeChallenge');
            window.location.href = `${process.env.REACT_APP_AUTH_URL}/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=${process.env.REACT_APP_FRONT_URL}/authorized&code_challenge=${codeChallenge}&code_challenge_method=S256`;
        }
        // eslint-disable-next-line
    }, []);

    return (
        <></>
    )
}