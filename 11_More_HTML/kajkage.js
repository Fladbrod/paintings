const list = [1, 2, 3, 4, 5];

// Mapper en til en
const squaredList = list.map(number => number * number);

console.log(squaredList)

// takes a predicate (Noget som er sandt eller falsk):
const isOdd = list.filter(number => number % 2 !== 0);
console.log(isOdd);


const kajkager = [{
    type: "Andrea",
    color: "blue",
    deliciousness: 8
}];

kajkager.push({
    type: "Kaj",
    color: "green",
    deliciousness: 10
});

const tbodyElement = document.getElementById("cake-tbody");

const hardCaketData = {
    type: "Kaj",
    color: "Green",
    deliciousness: 10
}

tbodyElement.innerHTML = `
    <tr>
            <td>${escapeHTML(hardCaketData.type.toString())}</td>
            <td>${escapeHTML(hardCaketData.color.toString())}</td>
            <td>${escapeHTML(hardCaketData.deliciousness.toString())}</td>
    </tr>
`;

// MED BACKTICKs KAN MAN HAVE KODE PÅ MANGE LINJER, kan  man ikke med "".
tbodyElement.innerHTML = `
    <tr>
        <td>Kaj</td>
        <td>Green</td>
        <td>10</td>
    </tr>
`;

escapeHTML()