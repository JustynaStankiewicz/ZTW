const { GraphQLServer } = require('graphql-yoga');
const axios = require("axios");


const resolvers = {
    Query: {
        users: () => getRestUsersList(),
        todos: () => getRestTodosList(),
        todo: (parent, args, context, info) => todoById(parent, args, context, info),
        user: (parent, args, context, info) => userById(parent, args, context, info),
        todosByUserId: (parent, args, context, info) => todosByUserId(parent, args, context, info)
    },
    User: {
        todos: (parent, args, context, info) => {
            return getRestTodosList().then((todosList) => {
                return todosList.filter(t => t.user_id == parent.id);
            });
        }
    },
    ToDoItem: {
        user: (parent, args, context, info) => {
            return getRestUsersList().then((usersList) => {
                return usersList.find(u => u.id == parent.user_id);
            });
        }
    },
};


const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
});
/*const usersList = [
    { id: 1, name: "Jan Konieczny", email: "jan.konieczny@wonet.pl", login: "jkonieczny" },
    { id: 2, name: "Anna Wesołowska", email: "anna.w@sad.gov.pl", login: "anna.wesolowska" },
    { id: 3, name: "Piotr Waleczny", email: "piotr.waleczny@gp.pl", login: "p.waleczny" }
];
const todosList = [
    { id: 1, title: "Naprawić samochód", completed: false, user_id: 3 },
    { id: 2, title: "Posprzątać garaż", completed: true, user_id: 3 },
    { id: 3, title: "Napisać e-mail", completed: false, user_id: 3 },
    { id: 4, title: "Odebrać buty", completed: false, user_id: 2 },
    { id: 5, title: "Wysłać paczkę", completed: true, user_id: 2 },
    { id: 6, title: "Zamówic kuriera", completed: false, user_id: 3 },
];*/
/*function todoById(parent, args, context, info){
    return todosList.find(t => t.id == args.id);
}
function userById(parent, args, context, info){
    return usersList.find(u => u.id == args.id);
}*/
async function todosByUserId(parent, args, context, info) {
    return getRestTodosList().then((todosList) => {
        return todosList.filter(t => t.user_id == args.userId);
    });
}
async function getRestUsersList() {
    try {
        const users = await axios.get("https://jsonplaceholder.typicode.com/users");
        return users.data.map(({ id, name, email, username }) => ({
            id: id,
            name: name,
            email: email,
            login: username,
        }));
    } catch (error) {
        throw error;
    }
}

async function getRestTodosList() {
    try {
        const todos = await axios.get("https://jsonplaceholder.typicode.com/todos");
        return todos.data.map(({ id, title, completed, userId }) => ({
            id: id,
            title: title,
            completed: completed,
            user_id: userId,
        }));
    } catch (error) {
        throw error;
    }
}

/*function usersByTodoId(parent, args, context, info) {
    return getRestTodosList().then((todosList) => {
        const usersIds = todosList
            .filter((t) => t.id == args.todoId)
            .map((t) => t.user_id);

        return getRestUsersList().then((usersList) => {
            return usersList.filter((u) => usersIds.includes(u.id));
        });
    });
}*/
function todoById(parent, args, context, info) {
    return getRestTodosList().then((todosList) => {
        return todosList.find(t => t.id == args.id);
    });
}

function userById(parent, args, context, info) {
    return getRestUsersList().then((usersList) => {
        return usersList.find(u => u.id == args.id);
    });
}

server.start(() => console.log(`Server is running on http://localhost:4000`));