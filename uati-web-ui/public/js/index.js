var DATA_CHARTS = {
  profiles: [],
  hashtags: [],
  tweetByHour: []
};

window.onload = function () {
  setInterval(function() { window.location.reload() }, 30 * 1000);

  axios.defaults.baseURL = 'http://localhost:8081';

  function formatTopTwitterProfile(data) {
    let profiles = [['Perfil', 'Seguidores',]];
    if (data) data.forEach(item => {
      profiles.push(['@' + item.screenName, item.followersCount])
    });
    DATA_CHARTS.profiles = profiles;
    google.charts.setOnLoadCallback(drawTopTwitterProfile);
  }

  function formatHashtagPt(data) {
    let hashtags = [];
    if (data) data.forEach(item => {
      hashtags.push([item.name, { v: item.total }])
    });
    DATA_CHARTS.hashtags = hashtags;
    google.charts.setOnLoadCallback(drawHashtagPt);
  }

  function formatTweetByHour(data) {
    let tweetByHour = [];
    if (data) data.forEach(item => {
      var dateFull = new Date(item.hour);
      var date = moment(dateFull).format('DD/MM/YYYY');
      var hour = moment(dateFull).format('HH');
      tweetByHour.push([hour, date, { v: item.total }])
    });
    DATA_CHARTS.tweetByHour = tweetByHour;
    google.charts.setOnLoadCallback(drawTweetByHour);
  }

  function getTopTwitterProfile() {
    return axios.get('/top_twitter_profile');
  }
  function getHashtagPt() {
    return axios.get('/hashtag_pt');
  }
  function getTweetByHour() {
    return axios.get('/tweet_by_hour');
  }

  axios.all([getTopTwitterProfile(), getHashtagPt(), getTweetByHour()])
    .then(axios.spread(function (topTwitterProfile, hashtagPt, tweetByHour) {
      formatTopTwitterProfile(topTwitterProfile.data);
      formatHashtagPt(hashtagPt.data);
      formatTweetByHour(tweetByHour.data);
      document.getElementById('container_loading').style.display = 'none';
      document.getElementById('container_body').style.display = 'block';
    }));
}
