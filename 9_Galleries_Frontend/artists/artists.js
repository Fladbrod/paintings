const featuredArtistWrapper = document.getElementById("featured-artists");
document.getElementById("create-artist-button").addEventListener("click", createArtist);

fetch("http://localhost:8080/artists")
    .then(response => response.json())
    .then(artists => {
        console.log(artists)
        featuredArtistWrapper.innerHTML = ``;
        artists.map(createArtistCard);
    });

function createArtist() {
    const name = document.getElementById("create-artist-name").value;
    const age = document.getElementById("create-artist-age").value;
    const image = document.getElementById("create-artist-image").value;
    const gender = document.getElementById("create-artist-gender").value;

    const newArtist = {
        name: name,
        age: Number(age),
        image: image,
        gender: gender
    }
    console.log(newArtist);

    fetch("http://localhost:8080/artists", {
        method: "POST",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(newArtist)
    })
        .then(response => {
            if (response.status === 200) {
                createArtistCard(newArtist);
            } else {
                console.log("Artist not created.", response.status);
            }
        })
        .catch(error => console.log("Network related error", error));

}

function createArtistCard(artist) {
    console.log(artist)
    const cardInfo = document.createElement("div");
    const cardImage = document.createElement("div");
    cardInfo.className = "cardinfo";
    cardImage.className = "cardimage";
    cardInfo.innerHTML = `
        <p>name: ${escapeHTML(artist.name)}</p>
        <p>age: ${escapeHTML(artist.age.toString())}</p>
        <p>gender: ${escapeHTML(artist.gender.toLowerCase())}</p>
`;

    cardImage.innerHTML = `
    <img alt="image" src="${(escapeHTML(artist.image))}">
    `;

    const card = document.createElement("div");
    card.className = "card";
    card.appendChild(cardInfo);
    card.appendChild(cardImage);
    featuredArtistWrapper.appendChild(card);
}