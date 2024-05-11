const template = document.createElement("template");
template.innerHTML = `
    <link rel="stylesheet" type="text/css" href="${import.meta.url.match(/.*\//)[0]}/studentlist.css"></link>
    <div id="wrapper">
        <p data-list>List is empty</p>
    </div>`;

const studenttable = document.createElement("template");
studenttable.innerHTML = `
    <table>
        <caption>List of students</caption>
        <thead><tr><th>First name</th><th>Last name</th><th>Study level</th><th></th></tr></thead>
        <tbody></tbody>
    </table>
`;

const studentrow = document.createElement("template");
studentrow.innerHTML = `
    <tr>
        <td></td><td></td><td></td><td></td>
    </tr>
`;

class StudentList extends HTMLElement {
    #shadow;
    #tbodyElm = null;
    #callbacks = new Map();
    #callbackId = 0;

    constructor() {
        // Always call super first in constructor
        super();

        // Entry point to the shadow DOM
        this.#shadow = this.attachShadow({ mode: 'closed' });
        const content = template.content.cloneNode(true);
        this.#shadow.appendChild(content);
    }

    setRemoveHandler(method) {
        this.#callbacks.set(this.#callbackId, method);
        const prevId = this.#callbackId;
        ++this.#callbackId;
        return prevId;
    }

    deleteRemoveHandler(handlerId) {
        this.#callbacks.delete(handlerId);
    }

    add(student) {
        this.#tbodyElm = this.#shadow.querySelector("tbody");
        if (this.#tbodyElm == null) {
            const wrapper = this.#shadow.getElementById("wrapper");
            wrapper.textContent = "";
            const content = studenttable.content.cloneNode(true);
            this.#tbodyElm = content.querySelector("tbody");
            wrapper.appendChild(content);
        }
        
        const rowcontent = studentrow.content.cloneNode(true);
        const row = rowcontent.firstElementChild;
        
        // Move children of rowcontent into shadow DOM
        this.#tbodyElm.prepend(rowcontent);
        
        row.setAttribute("data-studentid", student.id);

        row.cells[0].textContent = student.firstname;
        row.cells[1].textContent = student.lastname;
        row.cells[2].textContent = student.level.replace(/^./, match => match.toUpperCase());
        row.cells[2].setAttribute("data-level", student.level);
        const bt = document.createElement("button");
        bt.type = "button";
        bt.textContent = "Remove";
        bt.addEventListener("click", () => { this.#deleteStudent(student.id) });
        row.cells[3].appendChild(bt);
    }

    remove(id) {
        const row = this.#tbodyElm.querySelector(`tr[data-studentid="${id}"]`);
        if (row != null) {
            this.#tbodyElm.removeChild(row);
        }

        if (this.#tbodyElm.rows.length == 0) {
            const wrapper = this.#shadow.getElementById("wrapper");
            wrapper.textContent = "";
            this.#tbodyElm = null;
            wrapper.insertAdjacentHTML('beforeend', "<p data-list>List is empty</p>");
        }
    }

    #deleteStudent(studentId) {
        this.#callbacks.forEach(
            method => { method(studentId) }
        );
    }

    //Below is not used
    #getStudentFromHTML(studentId) {
        // = ${studentId}
        const row = this.#tbodyElm.querySelector(`tr[data-studentid="${studentId}"]`);
        const firstname = row.cells[0].textContent;
        const lastname = row.cells[1].textContent;
        const level = row.cells[2].getAttribute("data-level");;
        return { firstname: firstname, lastname: lastname, level: level, id: studentId };
    }
}

customElements.define('student-list', StudentList);

