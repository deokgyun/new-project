// TODO : 나중에 환경설정으로 환경에 따른 url 변경
const BASE_URL: string = `${process.env.NEXT_PUBLIC_API_URL}`

interface FetchOptions extends RequestInit {
    headers?: HeadersInit
    body?: BodyInit | null
}

const defaultHeaders = {
    'Content-Type': 'application/json',
    Authorization: `${process.env.NEXT_PUBLIC_TOKEN}`,
}

async function fetchWithDefaults(endpoint: string, options: FetchOptions = {}) {
    const url = `${BASE_URL}${endpoint}`
    const { headers, ...restOptions } = options

    const response = await fetch(url, {
        headers: {
            ...defaultHeaders,
            ...headers,
        },
        ...restOptions,
    })

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`)
    }

    return response.json()
}

export const api = {
    get: (endpoint: string, options?: FetchOptions) =>
        fetchWithDefaults(endpoint, { method: 'GET', ...options }),

    post: (endpoint: string, body: object, options?: FetchOptions) =>
        fetchWithDefaults(endpoint, {
            method: 'POST',
            body: JSON.stringify(body),
            headers: { 'Content-Type': 'application/json', ...options?.headers },
            ...options,
        }),
    put: (endpoint: string, body: object, options?: FetchOptions) =>
        fetchWithDefaults(endpoint, { method: 'PUT', body: JSON.stringify(body), ...options }),

    delete: (endpoint: string, options?: FetchOptions) =>
        fetchWithDefaults(endpoint, { method: 'DELETE', ...options }),
}
