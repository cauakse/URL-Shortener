import { useState } from 'react';
import Head from 'next/head';

interface ResponseApi {
  mensagem:string,
  erro:boolean,
  data:any
}

export default function Home() {
  const [url, setUrl] = useState('');
  const [loading, setLoading] = useState(false);
  const [shortUrl, setShortUrl] = useState('');
  const urlBase = "http://localhost:8080/redirect/";
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    
    try {
      const response: ResponseApi = await fetch("http://localhost:8080/api/url", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ 'urlUnshorted': url })
      }).then((res) => res.json());

      if (!response.erro && response.data) {
        setShortUrl(response.data);
      } else {
        console.error('Erro na resposta:', response.mensagem);
      }
      console.log(response.data);
      setShortUrl(urlBase+response.data.id);
    } catch (error) {
      console.error('Erro ao encurtar URL:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Head>
        <title>Encurtador de URL</title>
        <meta name="description" content="Encurte suas URLs de forma simples e rápida" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet" />
      </Head>

      <main className="min-h-screen bg-gradient-to-br from-[#640D5F] via-[#D91656] to-[#EB5B00] font-['Poppins']">
        <div className="container mx-auto px-4 py-16 flex flex-col items-center justify-center">
          <div className="w-full max-w-2xl bg-white/10 backdrop-blur-lg rounded-2xl p-8 shadow-xl">
            <h1 className="text-4xl md:text-5xl font-bold text-center text-white mb-4">
              Encurtador de URL
            </h1>
            <p className="text-lg md:text-xl text-center text-white/80 mb-8">
              Cole sua URL longa abaixo e receba uma versão curta instantaneamente
            </p>

            <form onSubmit={handleSubmit} className="space-y-4">
              <div className="flex flex-col md:flex-row gap-4">
                <div className="relative flex-1">
                  <input
                    type="url"
                    value={url}
                    onChange={(e) => setUrl(e.target.value)}
                    id="url-input"
                    className="flex-1 w-full px-4 py-3 rounded-xl bg-white/20 text-white border border-white/20 focus:border-white/40 focus:outline-none focus:ring-2 focus:ring-white/20 transition-all peer"
                    required
                  />
                  <label
                    htmlFor="url-input"
                    className="absolute left-4 top-3 text-white/50 transition-all duration-200 pointer-events-none
                    peer-focus:-translate-y-7 peer-focus:text-sm peer-focus:text-white
                    peer-valid:-translate-y-7 peer-valid:text-sm peer-valid:text-white"
                  >
                    URL
                  </label>
                </div>
                <button 
                  type="submit" 
                  className={`px-8 py-3 rounded-xl font-medium transition-all duration-200 ${
                    loading
                      ? 'bg-white/20 text-white/50 cursor-not-allowed'
                      : 'bg-[#FFB200] text-[#640D5F] hover:bg-[#FFB200]/90 hover:shadow-lg'
                  }`}
                  disabled={loading}
                >
                  {loading ? (
                    <span className="flex items-center">
                      <svg className="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                        <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                        <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                      </svg>
                      Encurtando...
                    </span>
                  ) : (
                    'Encurtar'
                  )}
                </button>
              </div>
            </form>

            {shortUrl && (
              <div className="mt-8 p-4 bg-white/20 rounded-xl">
                <p className="text-white text-center mb-2">URL encurtada:</p>
                <div className="flex items-center justify-between bg-white/30 rounded-lg p-3">
                  <code className="text-white flex-1 overflow-x-auto">{shortUrl}</code>
                  <button
                    onClick={() => navigator.clipboard.writeText(shortUrl)}
                    className="ml-4 p-2 text-white hover:bg-white/20 rounded-lg transition-all"
                    title="Copiar URL"
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 16H6a2 2 0 01-2-2V6a2 2 0 012-2h8a2 2 0 012 2v2m-6 12h8a2 2 0 002-2v-8a2 2 0 00-2-2h-8a2 2 0 00-2 2v8a2 2 0 002 2z" />
                    </svg>
                  </button>
                </div>
              </div>
            )}
          </div>
        </div>
      </main>
    </>
  );
}