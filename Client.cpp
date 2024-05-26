#define WIN32_LEAN_AND_MEAN

#include <Windows.h>
#include <iostream>
#include <WinSock2.h>
#include <WS2tcpip.h>
// Подключаем необходимые заголовочные файлы для работы с сокетами в Windows.

using namespace std;

int main() {
    WSADATA wsaData;
    // Структура для хранения данных о реализации Winsock.

    ADDRINFO hints;
    // Структура для хранения подсказок при получении адресной информации.

    ADDRINFO* addrResult;
    // Указатель на результат получения адресной информации.

    SOCKET ListenSocket = INVALID_SOCKET;
    // Сокет для прослушивания входящих подключений.

    SOCKET ConnectSocket = INVALID_SOCKET;
    // Сокет для обработки подключенного клиента.

    char recvBuffer[512];
    // Буфер для получения данных от клиента.

    const char* sendBuffer = "Hello from server";
    // Буфер для отправки данных клиенту.

    int result = WSAStartup(MAKEWORD(2, 2), &wsaData);
    // Инициализация использования Winsock версии 2.2.

    if (result != 0) {
        cout << "WSAStartup failed with result: " << result << endl;
        return 1;
    }

    ZeroMemory(&hints, sizeof(hints));
    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;
    hints.ai_flags = AI_PASSIVE;
    // Заполняем структуру hints подсказками для getaddrinfo: IPv4, TCP-сокет, протокол TCP, AI_PASSIVE для прослушивания входящих подключений.

    result = getaddrinfo(NULL, "666", &hints, &addrResult);
    // Получаем адресную информацию для всех доступных интерфейсов и порта "666" с использованием подсказок из hints.

    if (result != 0) {
        cout << "getaddrinfo failed with error: " << result << endl;
        freeaddrinfo(addrResult);
        WSACleanup();
        return 1;
    }

    ListenSocket = socket(addrResult->ai_family, addrResult->ai_socktype, addrResult->ai_protocol);
    // Создаем сокет для прослушивания на основе полученной адресной информации.

    if (ListenSocket == INVALID_SOCKET) {
        cout << "Socket creation failed" << endl;
        freeaddrinfo(addrResult);
        WSACleanup();
        return 1;
    }

    result = bind(ListenSocket, addrResult->ai_addr, (int)addrResult->ai_addrlen);
    // Привязываем ListenSocket к адресу и порту, полученным из addrResult.

    if (result == SOCKET_ERROR) {
        cout << "Bind failed, error: " << result << endl;
        closesocket(ListenSocket);
        freeaddrinfo(addrResult);
        WSACleanup();
        return 1;
    }

    result = listen(ListenSocket, SOMAXCONN);
    // Переводим ListenSocket в режим прослушивания входящих подключений с максимальной очередью.

    if (result == SOCKET_ERROR) {
        cout << "Listen failed, error: " << result << endl;
        closesocket(ListenSocket);
        freeaddrinfo(addrResult);
        WSACleanup();
        return 1;
    }

    ConnectSocket = accept(ListenSocket, NULL, NULL);
    // Принимаем входящее подключение и получаем новый сокет ConnectSocket для обработки клиента.

    if (ConnectSocket == INVALID_SOCKET) {
        cout << "Accept failed, error: " << WSAGetLastError() << endl;
        closesocket(ListenSocket);
        freeaddrinfo(addrResult);
        WSACleanup();
        return 1;
    }

    closesocket(ListenSocket);
    // Закрываем ListenSocket, так как он больше не нужен.

    do {
        ZeroMemory(recvBuffer, 512);
        result = recv(ConnectSocket, recvBuffer, 512, 0);
        // Получаем данные от клиента в recvBuffer.

        if (result > 0) {
            cout << "Received " << result << " bytes" << endl;
            cout << "Received data: " << recvBuffer << endl;

            result = send(ConnectSocket, sendBuffer, (int)strlen(sendBuffer), 0);
            // Отправляем ответное сообщение клиенту через ConnectSocket.

            if (result == SOCKET_ERROR) {
                cout << "Send failed, error: " << result << endl;
                closesocket(ConnectSocket);
                freeaddrinfo(addrResult);
                WSACleanup();
                return 1;
            }
        }
        else if (result == 0) {
            cout << "Connection closing" << endl;
        }
        else {
            cout << "Recv failed, error: " << WSAGetLastError() << endl;
            closesocket(ConnectSocket);
            freeaddrinfo(addrResult);
            WSACleanup();
            return 1;
        }
    } while (result > 0);

    result = shutdown(ConnectSocket, SD_SEND);
    // Отключаем отправку данных через ConnectSocket.

    if (result == SOCKET_ERROR) {
        cout << "Shutdown failed, error: " << result << endl;
        closesocket(ConnectSocket);
        freeaddrinfo(addrResult);
        WSACleanup();
        return 1;
    }

    closesocket(ConnectSocket);
    // Закрываем сокет ConnectSocket.

    freeaddrinfo(addrResult);
    // Освобождаем память, занятую addrResult.

    WSACleanup();
    // Завершаем использование Winsock.

    return 0;
}