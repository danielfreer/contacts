# Java Coding Challenge
You are being asked to create a simple contact entry system. You should create a REST API that will enable a client to
perform CRUD operations on the contact collection.

## Requirements
1. Create a new REST API using Java/JVM technologies with the following endpoints:

   | HTTP Method | Route          | Description            |
   |-------------|----------------|------------------------|
   | GET         | /contacts      | List all contacts      |
   | POST        | /contacts      | Create a new contact   |
   | PUT         | /contacts/{id} | Update a contact       |
   | GET         | /contacts/{id} | Get a specific contact |
   | DELETE      | /contacts/{id} | Delete a contact       |

2. The contact entry request when creating or updating a contact will be JSON and have the following format:

    <table>
    <tr><th>Format</th><th>Example</th></tr>
    <tr>
    <td>

    ```
    {
      "name": {
        "first": string,
        "middle": string,
        "last": string
      },
      "address": {
        "street": string,
        "city": string,
        "state": string,
        "zip": string
      },
      "phone": [
        {
          "number": string,
          "type": string ["home" | "work" | "mobile" ]
        }
      ],
      "email": string
    }
    ```

    </td>
    <td>

    ```json
    {
      "name": {
        "first": "Harold",
        "middle": "Francis",
        "last": "Gilkey"
      },
      "address": {
        "street": "8360 High Autumn Row",
        "city": "Cannon",
        "state": "Delaware",
        "zip": "19797"
      },
      "phone": [
        {
          "number": "302-611-9148",
          "type": "home"
        },
        {
          "number": "302-532-9427",
          "type": "mobile"
        }
      ],
      "email": "harold.gilkey@yahoo.com"
    }
    ```

    </td>
    </tr>
    </table>

3. Contact entries returned to the client by the API will be JSON and have the following format:

    <table>
    <tr><th>Format</th><th>Example</th></tr>
    <tr>
    <td>

    ```
    {
      "id": number,
      "name": {
        "first": string,
        "middle": string,
        "last": string
      },
      "address": {
        "street": string,
        "city": string,
        "state": string,
        "zip": string
      },
      "phone": [
        {
          "number": string,
          "type": string ["home" | "work" | "mobile"]
        }
      ],
      "email": string
    }
    ```

    </td>
    <td>

    ```json
    {
      "id": 101,
      "name": {
        "first": "Harold",
        "middle": "Francis",
        "last": "Gilkey"
      },
      "address": {
        "street": "8360 High Autumn Row",
        "city": "Cannon",
        "state": "Delaware",
        "zip": "19797"
      },
      "phone": [
        {
          "number": "302-611-9148",
          "type": "home"
        },
        {
          "number": "302-532-9427",
          "type": "mobile"
        }
      ],
      "email": "harold.gilkey@yahoo.com"
    }
    ```

    </td>
    </tr>
    </table>

4. Provide a storage mechanism for storing the contact entries. If you use a database you do not need to include the
   database, but you should include instructions to set up and create the database. In order to simplify the overall
   solution and minimize runtime dependencies, you are strongly encouraged to use an embedded database such as
   [H2](https://www.h2database.com/html/main.html), [HSQLDB](https://hsqldb.org/), or something similar.
5. Write unit tests to verify functionality where you deem it appropriate.
6. Upload all files and instructions for building the project (if necessary) to [GitHub](https://github.com/) and
   provide a link to us for review.
